package com.fulo.targets.domain.dto;

import java.util.List;
import java.util.UUID;

public record HitListDto(
        UUID id,
        String name,
        String description,
        Integer count,
        Double progress,
        List<TargetDto> targets
) {

}
