package com.fulo.targets.controllers;

import com.fulo.targets.domain.dto.TargetDto;
import com.fulo.targets.domain.entities.Target;
import com.fulo.targets.mappers.TargetMapper;
import com.fulo.targets.services.TargetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="/hit-lists/{hit_list_id}/targets")
public class TargetController {

    private final TargetService targetService;
    private final TargetMapper targetMapper;

    public TargetController(TargetService targetService, TargetMapper targetMapper) {
        this.targetService = targetService;
        this.targetMapper = targetMapper;
    }

    @GetMapping
    public List<TargetDto> listTargets(@PathVariable("hit_list_id") UUID hitListId) {

        return targetService.listTargets(hitListId)
                .stream()
                .map(targetMapper::toDto)
                .toList();
    }

    @PostMapping
    public TargetDto createTarget(@PathVariable("hit_list_id") UUID hitListId, @RequestBody TargetDto targetDto) {
        Target createdTarget = targetService.createTarget(
                hitListId,
                targetMapper.fromDto(targetDto)
        );

        return targetMapper.toDto(createdTarget);
    }

    @GetMapping(path = "/{target_id}")
    public Optional<TargetDto> getTarget(
            @PathVariable("hit_list_id") UUID hitListId,
            @PathVariable("target_id") UUID targetId
    ) {
        return targetService.getTarget(hitListId, targetId).map(targetMapper::toDto);
    }

    @PutMapping(path = "/{target_id}")
    public TargetDto updateTarget(
            @PathVariable("hit_list_id") UUID hitListId,
            @PathVariable("target_id") UUID targetId,
            @RequestBody TargetDto targetDto
    ) {
        Target updatedTarget = targetService.updateTarget(
                hitListId,
                targetId,
                targetMapper.fromDto(targetDto)
        );

        return targetMapper.toDto(updatedTarget);
    }

    @DeleteMapping(path = "/{target_id}")
    public void deleteTarget(
            @PathVariable("hit_list_id") UUID hitListId,
            @PathVariable("target_id") UUID targetId
    ) {
        targetService.deleteTarget(hitListId, targetId);
    }
}
