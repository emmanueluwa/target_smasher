package com.fulo.targets.mappers.impl;

import com.fulo.targets.domain.dto.HitListDto;
import com.fulo.targets.domain.entities.HitList;
import com.fulo.targets.domain.entities.Target;
import com.fulo.targets.domain.entities.TargetStatus;
import com.fulo.targets.mappers.HitListMapper;
import com.fulo.targets.mappers.TargetMapper;

import java.util.List;
import java.util.Optional;

public class HitListMapperImpl implements HitListMapper {

    private final TargetMapper targetMapper;

    public HitListMapperImpl(TargetMapper targetMapper) {
        this.targetMapper = targetMapper;
    }

    @Override
    public HitList fromDto(HitListDto hitListDto) {
        return new HitList(
                hitListDto.id(),
                hitListDto.name(),
                hitListDto.description(),
                Optional.ofNullable(hitListDto.targets())
                        .map(targets -> targets.stream()
                                .map(targetMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public HitListDto toDto(HitList hitList) {
        return new HitListDto(
                hitList.getId(),
                hitList.getName(),
                hitList.getDescription(),
                Optional.ofNullable(hitList.getTargets())
                        .map(List::size)
                        .orElse(0),
                calculateHitListProgress(hitList.getTargets()),
                Optional.ofNullable(hitList.getTargets())
                        .map(targets ->
                                targets.stream().map(targetMapper::toDto).toList()
                        ).orElse(null)

        );
    }

    //TODO: could be more refined
    private Double calculateHitListProgress(List<Target> targets) {
        if (null == targets) {
            return null;
        }

        long closedTargetCount = targets.stream().filter(target ->
                    TargetStatus.HIT == target.getStatus()
                ).count();

        return (double) closedTargetCount / targets.size();
    }
}
