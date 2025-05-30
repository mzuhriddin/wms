package org.example.wms.mapper;

import org.example.wms.dto.InputDTO;
import org.example.wms.dto.InputProductDTO;
import org.example.wms.entity.InputEntity;
import org.example.wms.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InputMapper extends BaseMapper<InputEntity, InputDTO> {

    default InputDTO toDTO(InputEntity order) {
        InputDTO build = InputDTO.builder()
                .createdDate(order.getCreatedDate())
                .userId(order.getUserEntity().getId())
                .items(order.getItems().stream().map(item -> InputProductDTO.builder()
                        .productId(item.getProductEntity().getId())
                        .productName(item.getProductEntity().getName())
                        .quantity(item.getQuantity())
                        .build()).toList())
                .build();
        build.setId(order.getId());
        return build;
    }
}
