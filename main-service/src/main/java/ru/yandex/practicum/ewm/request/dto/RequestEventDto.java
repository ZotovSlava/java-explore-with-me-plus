package ru.yandex.practicum.ewm.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yandex.practicum.ewm.request.model.RequestStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequestEventDto {
    private Long id;

    private LocalDateTime created;

    private Long requester;

    private Long event;

    private RequestStatus status;

}
