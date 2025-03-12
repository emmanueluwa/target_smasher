package com.fulo.targets.domain.dto;

import com.fulo.targets.domain.entities.TargetPriority;
import com.fulo.targets.domain.entities.TargetStatus;

import java.util.UUID;

//representing tasks in rest api
public record TargetDto(
        UUID id,
        String name,
        String description,
        String why,
        TargetPriority priority,
        TargetStatus status
) {


}
