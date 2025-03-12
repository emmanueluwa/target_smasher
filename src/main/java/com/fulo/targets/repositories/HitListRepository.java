package com.fulo.targets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HitListRepository extends JpaRepository<List, UUID> {

}
