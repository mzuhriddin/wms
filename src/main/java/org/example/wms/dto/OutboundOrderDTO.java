package org.example.wms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutboundOrderDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    private Long userId;
    private List<OutboundOrderItemDTO> items;

}
