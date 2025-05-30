package org.example.wms.repository;

import org.example.wms.entity.OutputProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProductEntity, Long> {
}