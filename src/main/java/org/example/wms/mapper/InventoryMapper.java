package org.example.wms.mapper;

import org.example.wms.dto.InventoryDTO;
import org.example.wms.entity.InventoryEntity;
import org.example.wms.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper extends BaseMapper<InventoryEntity, InventoryDTO> {

    default InventoryDTO toDTO(InventoryEntity inventoryEntity) {
        InventoryDTO build = InventoryDTO.builder()
                .productId(inventoryEntity.getProductEntity().getId())
                .productName(inventoryEntity.getProductEntity().getName())
                .warehouseId(inventoryEntity.getWarehouseEntity().getId())
                .warehouseName(inventoryEntity.getWarehouseEntity().getName())
                .quantity(inventoryEntity.getQuantity())
                .lastUpdated(inventoryEntity.getLastUpdated())
                .build();
        build.setId(inventoryEntity.getId());
        return build;
    }
}