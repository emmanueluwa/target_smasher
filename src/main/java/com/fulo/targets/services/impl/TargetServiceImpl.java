package com.fulo.targets.services.impl;

import com.fulo.targets.domain.entities.Target;
import com.fulo.targets.repositories.TargetRepository;
import com.fulo.targets.services.TargetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TargetServiceImpl implements TargetService {

    private final TargetRepository targetRepository;

    public TargetServiceImpl(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public List<Target> listTargets(UUID targetListId) {
        return targetRepository.findByHitListId(targetListId);
    }
}
