package org.example.wms.repository;

import org.example.wms.entity.InputProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputProductRepository extends JpaRepository<InputProductEntity, Long> {
}