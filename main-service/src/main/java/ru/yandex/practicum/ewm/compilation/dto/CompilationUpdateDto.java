package ru.yandex.practicum.ewm.compilation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CompilationUpdateDto {
    private String title;

    private Boolean pinned;

    private List<Integer> events;

}
