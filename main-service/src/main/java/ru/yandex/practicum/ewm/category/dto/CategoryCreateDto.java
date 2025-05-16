package ru.yandex.practicum.ewm.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryCreateDto {
    @NotBlank(message = "Name can not be blank")
    private String name;
}
