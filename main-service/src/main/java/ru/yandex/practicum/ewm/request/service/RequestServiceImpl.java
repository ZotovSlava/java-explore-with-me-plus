package ru.yandex.practicum.ewm.request.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.ewm.exception.CompilationNotFoundException;
import ru.yandex.practicum.ewm.request.dto.RequestDto;
import ru.yandex.practicum.ewm.request.mapper.RequestMapper;
import ru.yandex.practicum.ewm.request.model.Request;
import ru.yandex.practicum.ewm.request.model.RequestStatus;
import ru.yandex.practicum.ewm.request.storage.RequestRepository;
import ru.yandex.practicum.ewm.user.storage.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Override
    public RequestDto create(Long userId, Long eventId) {
        return null;
    }

    @Override
    public List<RequestDto> get(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new CompilationNotFoundException(userId));

        List<Request> requests = requestRepository.findAllByRequesterId(userId);

        return requests.stream()
                .map(RequestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestDto update(Long userId, Long requestId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new CompilationNotFoundException(userId));

        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new CompilationNotFoundException(requestId));

        request.setStatus(RequestStatus.CANCELED);

        return RequestMapper.toDto(
                requestRepository.save(request)
        );
    }
}
