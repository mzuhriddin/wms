package org.example.wms.repository;

import org.example.wms.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProduct_ProductIdAndWarehouse_WarehouseId(Long productId, Long warehouseId);
}
