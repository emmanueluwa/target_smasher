package com.fulo.targets.controllers;

import com.fulo.targets.domain.dto.HitListDto;
import com.fulo.targets.domain.entities.HitList;
import com.fulo.targets.mappers.HitListMapper;
import com.fulo.targets.services.HitListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping(path = "/{hit_list_id}")
    public Optional<HitListDto> getHitList(@PathVariable("hit_list_id") UUID hitListId) {
        return hitListService.getHitList(hitListId).map(hitListMapper::toDto);
    }

    @PutMapping(path = "/{hit_list_id}")
    public HitListDto updateHitList(
            @PathVariable("hit_list_id") UUID hitListId,
            @RequestBody HitListDto hitListDto
    ) {
        HitList updatedHitList = hitListService.updateHitList(
                hitListId,
                hitListMapper.fromDto(hitListDto)
        );

        return hitListMapper.toDto(updatedHitList);
    }

    @DeleteMapping(path = "/{hit_list_id}")
    public void deleteHitList(@PathVariable("hit_list_id") UUID hitListId) {
        hitListService.deleteHitList(hitListId);
    }
}
