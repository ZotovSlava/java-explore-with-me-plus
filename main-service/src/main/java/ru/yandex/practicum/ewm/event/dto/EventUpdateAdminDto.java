package ru.yandex.practicum.ewm.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.ewm.event.model.EventStateAction;
import ru.yandex.practicum.ewm.event.model.Location;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EventUpdateAdminDto {

    private String annotation;
    private Long category;
    private String description;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Location location;
    private Boolean paid;

    @PositiveOrZero(message = "Number must be zero or positive")
    private Integer participantLimit;
    private Boolean requestModeration;
    private EventStateAction stateAction;
    private String title;
}
