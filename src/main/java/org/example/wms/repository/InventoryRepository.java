package org.example.wms.repository;

import org.example.wms.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProductIdAndWarehouseId(Long productId, Long warehouseId);
}
