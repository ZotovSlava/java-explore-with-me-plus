package ru.yandex.practicum.ewm.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.yandex.practicum.ewm.event.model.Location;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EventCreateDto {
    @NotBlank(message = "Annotation cannot be blank.")
    @Length(min = 20, max = 2000)
    private String annotation;

    private Long category;

    @NotBlank(message = "Description cannot be blank.")
    @Length(min = 20, max = 7000)
    private String description;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created = LocalDateTime.now();

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent()
    private LocalDateTime eventDate;

    private Location location;

    private Boolean paid;

    @PositiveOrZero(message = "Number must be zero or positive")
    private Integer participantLimit;

    private Boolean requestModeration;

    @NotBlank(message = "Title cannot be blank.")
    @Length(min = 3, max = 120)
    private String title;

    private Integer views = 0;
}
