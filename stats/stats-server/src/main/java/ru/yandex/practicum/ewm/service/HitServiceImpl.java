package ru.yandex.practicum.ewm.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.dto.HitDto;
import ru.practicum.dto.StatsDto;
import ru.yandex.practicum.ewm.mapper.Mapper;
import ru.yandex.practicum.ewm.model.Hit;
import ru.yandex.practicum.ewm.storage.HitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class HitServiceImpl implements HitService {

    private final HitRepository hitRepository;

    @Override
    public void save(HitDto hitDto) {
        Hit hit = Mapper.toEntityHit(hitDto);

        hitRepository.save(hit);
    }

    @Override
    public ResponseEntity<List<StatsDto>> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }

        List<StatsDto> stats;

        if (unique) {
            stats = hitRepository.findUniqueHitsByPeriodAndUris(start, end, uris);
        } else {
            stats = hitRepository.findAllHitsByPeriodAndUris(start, end, uris);
        }

        return ResponseEntity.ok(stats);
    }
}
