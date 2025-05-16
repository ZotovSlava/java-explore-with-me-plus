package ru.yandex.practicum.ewm.category.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.ewm.category.dto.CategoryCreateDto;
import ru.yandex.practicum.ewm.category.dto.CategoryRequestDto;
import ru.yandex.practicum.ewm.category.mapper.CategoryMapper;
import ru.yandex.practicum.ewm.category.model.Category;
import ru.yandex.practicum.ewm.category.storage.CategoryRepository;
import ru.yandex.practicum.ewm.exception.CategoryNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryRequestDto create(CategoryCreateDto categoryCreateDto) {
        return CategoryMapper.toRequestDto(
                categoryRepository.save(
                        CategoryMapper.toEntity(categoryCreateDto)
                )
        );
    }

    @Override
    public CategoryRequestDto update(CategoryCreateDto categoryCreateDto, Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new CategoryNotFoundException(catId));

        category.setName(categoryCreateDto.getName());

        return CategoryMapper.toRequestDto(
                categoryRepository.save(category)
        );
    }

    @Override
    public void delete(Long catId) {
        categoryRepository.findById(catId)
                .orElseThrow(() -> new CategoryNotFoundException(catId));

        // Проверка, что с категорией не связаны события
        // {
        //  "status": "CONFLICT",
        //  "reason": "For the requested operation the conditions are not met.",
        //  "message": "The category is not empty",
        //  "timestamp": "2023-01-21 16:56:19"
        //}

        categoryRepository.deleteById(catId);
    }

    @Override
    public List<CategoryRequestDto> get(int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);

        Page<Category> page = categoryRepository.findAll(pageable);

        return page.stream()
                .map(CategoryMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryRequestDto getById(Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new CategoryNotFoundException(catId));

        return CategoryMapper.toRequestDto(
                categoryRepository.save(category)
        );
    }
}
