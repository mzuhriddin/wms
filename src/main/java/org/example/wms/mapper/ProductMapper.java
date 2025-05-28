package org.example.wms.mapper;

import org.example.wms.dto.ProductDTO;
import org.example.wms.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO dto);
}
