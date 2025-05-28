package org.example.wms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDTO {
    private Long inventoryId;
    private Long productId;
    private String productName;
    private Long warehouseId;
    private String warehouseName;
    private Integer quantity;
    private LocalDateTime lastUpdated;
}
