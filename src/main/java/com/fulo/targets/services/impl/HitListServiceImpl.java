package com.fulo.targets.services.impl;

import com.fulo.targets.domain.entities.HitList;
import com.fulo.targets.repositories.HitListRepository;
import com.fulo.targets.services.HitListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public HitList createHitList(HitList hitList) {
        if(null != hitList.getId()) {
            throw new IllegalArgumentException("Hit list already has an ID!");
        }
        if(null == hitList.getName() || hitList.getName().isBlank()) {
            throw new IllegalArgumentException("Hit list name must be present.");
        }

        LocalDateTime now = LocalDateTime.now();
        return hitListRepository.save(new HitList(
                null,
                hitList.getName(),
                hitList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<HitList> getHitList(UUID id) {
        return hitListRepository.findById(id);
    }


}
