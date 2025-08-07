package org.example.wms.service;

import org.example.wms.dto.InventoryDTO;
import org.example.wms.entity.InventoryEntity;
import org.example.wms.entity.ProductEntity;
import org.example.wms.entity.WarehouseEntity;
import org.example.wms.mapper.InventoryMapper;
import org.example.wms.repository.InventoryRepository;
import org.example.wms.service.base.CRUDService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService extends CRUDService<InventoryRepository, InventoryEntity, InventoryDTO, InventoryMapper> {

    protected InventoryService(InventoryRepository repository, InventoryMapper mapper) {
        super(repository, mapper);
    }

    public Optional<InventoryEntity> findByProductAndWarehouse(ProductEntity product, WarehouseEntity warehouse) {
        return repository.findByProductEntityAndWarehouseEntity(product, warehouse);
    }
}
