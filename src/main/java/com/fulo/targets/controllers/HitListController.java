package com.fulo.targets.controllers;

import com.fulo.targets.domain.dto.HitListDto;
import com.fulo.targets.domain.entities.HitList;
import com.fulo.targets.mappers.HitListMapper;
import com.fulo.targets.services.HitListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//marking as bean
@RestController
@RequestMapping(path = "/hit-lists")
public class HitListController {

    private final HitListService hitListService;
    private final HitListMapper hitListMapper;

    public HitListController(HitListService hitListService, HitListMapper hitListMapper) {
        this.hitListService = hitListService;
        this.hitListMapper = hitListMapper;
    }

    @GetMapping
    public List<HitListDto> listHitLists() {
        return hitListService.listHitLists()
                .stream()
                .map(hitListMapper::toDto)
                .toList();
    }

    @PostMapping
    public HitListDto createHitList(@RequestBody HitListDto hitListDto) {
        HitList createdHitList = hitListService.createHitList(
                hitListMapper.fromDto(hitListDto)
        );

        return hitListMapper.toDto(createdHitList);
    }
}
