package org.example.wms.mapper;

import org.example.wms.dto.InboundOrderDTO;
import org.example.wms.dto.InboundOrderItemDTO;
import org.example.wms.entity.InboundOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InboundOrderMapper {
    default InboundOrderDTO toDTO(InboundOrder order) {
        return InboundOrderDTO.builder()
                .orderId(order.getId())
                .orderDate(order.getCreatedAt())
                .userId(order.getUser().getId())
                .items(order.getItems().stream().map(item -> InboundOrderItemDTO.builder()
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .build()).toList())
                .build();
    }
}
