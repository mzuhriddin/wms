package org.example.wms.mapper;

import org.example.wms.dto.InventoryDTO;
import org.example.wms.entity.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    default InventoryDTO toDTO(Inventory inventory) {
        return InventoryDTO.builder()
                .inventoryId(inventory.getId())
                .productId(inventory.getProduct().getId())
                .productName(inventory.getProduct().getName())
                .warehouseId(inventory.getWarehouse().getId())
                .warehouseName(inventory.getWarehouse().getName())
                .quantity(inventory.getQuantity())
                .lastUpdated(inventory.getLastUpdated())
                .build();
    }
}