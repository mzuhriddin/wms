package org.example.wms.repository;

import org.example.wms.entity.InventoryEntity;
import org.example.wms.entity.ProductEntity;
import org.example.wms.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    Optional<InventoryEntity> findByProductEntityAndWarehouseEntity(ProductEntity product, WarehouseEntity warehouse);
}
