package com.fulo.targets.services;

import com.fulo.targets.domain.entities.Target;

import java.util.List;
import java.util.UUID;

public interface TargetService {
    List<Target> listTargets(UUID targetListId);
}
