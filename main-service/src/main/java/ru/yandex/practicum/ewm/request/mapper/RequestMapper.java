package ru.yandex.practicum.ewm.request.mapper;

import ru.yandex.practicum.ewm.request.dto.RequestDto;
import ru.yandex.practicum.ewm.request.model.Request;
import ru.yandex.practicum.ewm.request.model.RequestStatus;
import ru.yandex.practicum.ewm.user.model.User;

import java.time.LocalDateTime;

public class RequestMapper {
    public static Request toEntity(User requester, Event event, RequestStatus status) {
        Request request = new Request();
        request.setEvent(event);
        request.setRequester(requester);
        request.setCreated(LocalDateTime.now());
        request.setStatus(status);

        return request;
    }

    public static RequestDto toDto(Request request) {
        return new RequestDto(
                request.getId(),
                request.getCreated(),
                request.getRequester(),
                request.getEvent(),
                request.getStatus()
        );
    }
}
