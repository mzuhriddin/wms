package org.example.wms.mapper;

import org.example.wms.dto.OutputDTO;
import org.example.wms.dto.OutputProductDTO;
import org.example.wms.entity.OutputEntity;
import org.example.wms.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutputMapper extends BaseMapper<OutputEntity, OutputDTO> {
    default OutputDTO toDTO(OutputEntity order) {
        OutputDTO build = OutputDTO.builder()
                .createdDate(order.getCreatedDate())
                .userId(order.getUserEntity().getId())
                .items(order.getItems().stream().map(item -> OutputProductDTO.builder()
                        .productId(item.getProductEntity().getId())
                        .productName(item.getProductEntity().getName())
                        .quantity(item.getQuantity())
                        .build()
                ).toList())
                .build();
        build.setId(order.getId());
        return build;
    }
}