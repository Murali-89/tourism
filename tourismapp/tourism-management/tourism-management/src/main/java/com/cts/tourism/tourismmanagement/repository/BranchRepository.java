package com.cts.tourism.tourismmanagement.repository;

import com.cts.tourism.tourismmanagement.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public  interface BranchRepository extends JpaRepository<BranchEntity, Long> {
    Optional<BranchEntity> findByPlace(String place);
}
