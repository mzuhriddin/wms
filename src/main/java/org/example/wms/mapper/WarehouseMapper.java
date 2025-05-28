package org.example.wms.mapper;

import org.example.wms.dto.WarehouseDTO;
import org.example.wms.entity.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseDTO toDTO(Warehouse warehouse);
    Warehouse toEntity(WarehouseDTO dto);
}