package com.fulo.targets.services.impl;

import com.fulo.targets.domain.entities.HitList;
import com.fulo.targets.domain.entities.Target;
import com.fulo.targets.domain.entities.TargetPriority;
import com.fulo.targets.domain.entities.TargetStatus;
import com.fulo.targets.repositories.HitListRepository;
import com.fulo.targets.repositories.TargetRepository;
import com.fulo.targets.services.TargetService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TargetServiceImpl implements TargetService {

    private final TargetRepository targetRepository;
    private final HitListRepository hitListRepository;

    public TargetServiceImpl(TargetRepository targetRepository, HitListRepository hitListRepository) {
        this.targetRepository = targetRepository;
        this.hitListRepository = hitListRepository;
    }

    @Override
    public List<Target> listTargets(UUID targetListId) {
        return targetRepository.findByHitListId(targetListId);
    }

    @Override
    public Target createTarget(UUID hitListId, Target target) {
        if(null != target.getId()) {
            throw new IllegalArgumentException("Target already has an ID");
        }
        if(null == target.getName() || target.getName().isBlank()) {
            throw new IllegalArgumentException("Target must have a name");
        }

        TargetPriority targetPriority = Optional.ofNullable(target.getPriority())
                .orElse(TargetPriority.MEDIUM);

        TargetStatus targetStatus = TargetStatus.MISS;

        HitList hitList = hitListRepository.findById(hitListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Hit List id provided"));

        LocalDateTime now = LocalDateTime.now();

        Target targetToSave = new Target(
                null,
                target.getName(),
                target.getDescription(),
                target.getWhy(),
                targetStatus,
                targetPriority,
                hitList,
                now,
                now
        );

        return targetRepository.save(targetToSave);
    }
}
