package org.example.wms.mapper;

import org.example.wms.dto.OutboundOrderDTO;
import org.example.wms.dto.OutboundOrderItemDTO;
import org.example.wms.entity.OutboundOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutboundOrderMapper {
    default OutboundOrderDTO toDTO(OutboundOrder order) {
        return OutboundOrderDTO.builder()
                .orderId(order.getId())
                .orderDate(order.getCreatedAt())
                .userId(order.getUser().getId())
                .items(order.getItems().stream().map(item -> OutboundOrderItemDTO.builder()
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .build()
                ).toList())
                .build();
    }
}