package org.example.wms.mapper;

import org.example.wms.dto.ProductDTO;
import org.example.wms.entity.ProductEntity;
import org.example.wms.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductEntity, ProductDTO> {
}
