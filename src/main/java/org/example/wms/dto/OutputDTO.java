package org.example.wms.dto;

import lombok.*;
import org.example.wms.dto.general.BaseDTO;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutputDTO extends BaseDTO {
    private LocalDateTime createdDate;
    private Long warehouseId;
    private Long userId;
    private List<OutputProductDTO> items;

}
