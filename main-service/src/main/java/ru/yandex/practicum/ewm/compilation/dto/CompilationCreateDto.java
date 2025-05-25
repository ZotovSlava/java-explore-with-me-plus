package ru.yandex.practicum.ewm.compilation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CompilationCreateDto {
    @NotBlank(message = "Title can not be blank")
    private String title;

    private Boolean pinned = false;

    private List<Long> events;
}
