package com.fulo.targets.services.impl;

import com.fulo.targets.domain.entities.HitList;
import com.fulo.targets.repositories.HitListRepository;
import com.fulo.targets.services.HitListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HitListServiceImpl implements HitListService {

    private final HitListRepository hitListRepository;

    public HitListServiceImpl(HitListRepository hitListRepository) {
        this.hitListRepository = hitListRepository;
    }

    @Override
    public List<HitList> listHitLists() {
        return hitListRepository.findAll();
    }
}
