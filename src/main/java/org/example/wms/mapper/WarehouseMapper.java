package org.example.wms.mapper;

import org.example.wms.dto.WarehouseDTO;
import org.example.wms.entity.WarehouseEntity;
import org.example.wms.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper extends BaseMapper<WarehouseEntity, WarehouseDTO> {
}