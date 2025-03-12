package com.fulo.targets.controllers;

import com.fulo.targets.domain.dto.HitListDto;
import com.fulo.targets.mappers.HitListMapper;
import com.fulo.targets.services.HitListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/test")
    public String test() {
        return "It works!";
    }
}
