package ru.yandex.practicum.ewm.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.ewm.exception.UserNotFoundException;
import ru.yandex.practicum.ewm.user.dto.UserCreateDto;
import ru.yandex.practicum.ewm.user.dto.UserRequestDto;
import ru.yandex.practicum.ewm.user.mapper.UserMapper;
import ru.yandex.practicum.ewm.user.storage.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserRequestDto create(UserCreateDto userCreateDto) {
        return UserMapper.toRequestDto(
                userRepository.save(
                        UserMapper.toEntity(userCreateDto)
                )
        );
    }

    @Override // дописать
    public List<UserRequestDto> get(List<Integer> ids, int from, int size) {
        return List.of();
    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.deleteById(userId);
    }
}
