package com.fulo.targets.services;

import com.fulo.targets.domain.entities.HitList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HitListService {

    List<HitList> listHitLists();

    HitList createHitList(HitList hitList);

    Optional<HitList> getHitList(UUID id);

    HitList updateHitList(UUID hitListId, HitList hitList);

    void deleteHitList(UUID hitListId);
}
