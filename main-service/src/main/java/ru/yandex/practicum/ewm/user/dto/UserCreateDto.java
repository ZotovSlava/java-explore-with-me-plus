package ru.yandex.practicum.ewm.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateDto {
    @NotBlank(message = "Name can not be blank")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email can not be blank")
    private String email;
}
