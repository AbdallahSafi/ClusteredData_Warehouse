package com.clusterData.warehouse.repositories;

import com.clusterData.warehouse.models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, String> {
}
