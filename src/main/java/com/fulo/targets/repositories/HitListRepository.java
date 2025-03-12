package com.fulo.targets.repositories;

import com.fulo.targets.domain.entities.HitList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HitListRepository extends JpaRepository<HitList, UUID> {

}
