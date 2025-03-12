package com.fulo.targets.mappers.impl;

import com.fulo.targets.domain.dto.TargetDto;
import com.fulo.targets.domain.entities.Target;
import com.fulo.targets.mappers.TargetMapper;
import org.springframework.stereotype.Component;

@Component
public class TargetMapperImpl implements TargetMapper {

    @Override
    public Target fromDto(TargetDto targetDto) {
        return new Target(
                targetDto.id(),
                targetDto.description(),
                targetDto.why(),
                targetDto.status(),
                targetDto.name(),
                targetDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TargetDto toDto(Target target) {
        return new TargetDto(
                target.getId(),
                target.getName(),
                target.getDescription(),
                target.getWhy(),
                target.getPriority(),
                target.getStatus()
        );
    }
}
