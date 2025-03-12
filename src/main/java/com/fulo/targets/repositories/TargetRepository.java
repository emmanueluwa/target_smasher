package com.fulo.targets.repositories;

import com.fulo.targets.domain.entities.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TargetRepository extends JpaRepository<Target, UUID> {

    List<Target> findByHitListId(UUID hitListId);
    Optional<Target> findByHitListIdAndId(UUID hitListId, UUID id);
}
