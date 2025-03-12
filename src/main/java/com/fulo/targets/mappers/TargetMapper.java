package com.fulo.targets.mappers;

import com.fulo.targets.domain.dto.TargetDto;
import com.fulo.targets.domain.entities.Target;

public interface TargetMapper {

    Target fromDto(TargetDto targetDto);

    TargetDto toDto(Target target);
}
