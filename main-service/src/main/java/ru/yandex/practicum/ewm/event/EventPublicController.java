package ru.yandex.practicum.ewm.event;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.ewm.event.dto.EventFullDto;
import ru.yandex.practicum.ewm.event.dto.EventShortDto;
import ru.yandex.practicum.ewm.event.model.EventPublicSort;
import ru.yandex.practicum.ewm.event.model.PublicEventParams;
import ru.yandex.practicum.ewm.event.service.EventService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventPublicController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventShortDto>> get(
            @RequestParam String text,
            @RequestParam Set<Long> categories,
            @RequestParam Boolean paid,
            @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
            @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
            @RequestParam Boolean onlyAvailable,
            @RequestParam EventPublicSort sort,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        //log.info("client ip: {}", request.getRemoteAddr());
        PublicEventParams publicEventParams = new PublicEventParams();
        publicEventParams.setText(text);
        publicEventParams.setCategories(categories);
        publicEventParams.setPaid(paid);
        publicEventParams.setRangeStart(rangeStart);
        publicEventParams.setRangeEnd(rangeEnd);
        publicEventParams.setOnlyAvailable(onlyAvailable);
        publicEventParams.setSort(sort);
        publicEventParams.setFrom(from);
        publicEventParams.setSize(size);
        publicEventParams.setIpAdr(request.getRemoteAddr());
        log.info("--> GET запрос /events с параметрами {}", publicEventParams);
        List<EventShortDto> events = eventService.getPublic(publicEventParams);
        log.info("<-- GET запрос /events вернул ответ: {}", events);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventFullDto> getById(
            @PathVariable("id") Long eventId,
            HttpServletRequest request
    ) {
        PublicEventParams publicEventParams = new PublicEventParams();
        publicEventParams.setIpAdr(request.getRemoteAddr());
        log.info("--> GET запрос /events/{}", eventId);
        EventFullDto event = eventService.getByIdPublic(eventId, publicEventParams);
        log.info("<-- GET запрос /events/{} вернул ответ: {}", eventId, event);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(event);
    }

}
