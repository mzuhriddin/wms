package org.example.wms.dto;

import lombok.*;
import org.example.wms.dto.general.BaseDTO;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDTO extends BaseDTO {
    private Long productId;
    private String productName;
    private Long warehouseId;
    private String warehouseName;
    private Integer quantity;
    private LocalDateTime lastUpdated;
}
