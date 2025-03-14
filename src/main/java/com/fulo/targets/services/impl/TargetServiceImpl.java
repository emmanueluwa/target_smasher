package com.fulo.targets.services.impl;

import com.fulo.targets.domain.entities.HitList;
import com.fulo.targets.domain.entities.Target;
import com.fulo.targets.domain.entities.TargetPriority;
import com.fulo.targets.domain.entities.TargetStatus;
import com.fulo.targets.repositories.HitListRepository;
import com.fulo.targets.repositories.TargetRepository;
import com.fulo.targets.services.TargetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    @Transactional
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

    @Override
    public Optional<Target> getTarget(UUID hitListId, UUID targetId) {
        return targetRepository.findByHitListIdAndId(hitListId, targetId);
    }

    @Transactional
    @Override
    public Target updateTarget(UUID hitListId, UUID targetId, Target target) {
        if(null == target.getId()) {
            throw new IllegalArgumentException("Target must have an ID!");
        }
        if(!Objects.equals(targetId, target.getId())) {
            throw new IllegalArgumentException("Target IDs do not match");
        }
        if(null == target.getPriority()) {
            throw new IllegalArgumentException("Target must have a priority");
        }
        if(null == target.getStatus()) {
            throw new IllegalArgumentException("Target must have a status");
        }

        Target existingTarget = targetRepository.findByHitListIdAndId(hitListId, targetId)
                .orElseThrow(() -> new IllegalArgumentException("Target not found"));

        existingTarget.setName(target.getName());
        existingTarget.setDescription(target.getDescription());
        existingTarget.setWhy(target.getWhy());
        existingTarget.setPriority(target.getPriority());
        existingTarget.setStatus(target.getStatus());
        existingTarget.setUpdated(LocalDateTime.now());

        return targetRepository.save(existingTarget);
    }

    //custom delete method add transactional annotation
    @Transactional
    @Override
    public void deleteTarget(UUID hitListId, UUID targetId) {
        targetRepository.deleteByHitListIdAndId(hitListId, targetId);
    }
}
