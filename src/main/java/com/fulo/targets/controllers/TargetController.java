package com.fulo.targets.controllers;

import com.fulo.targets.domain.dto.TargetDto;
import com.fulo.targets.mappers.TargetMapper;
import com.fulo.targets.services.TargetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

}
