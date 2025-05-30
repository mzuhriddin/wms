package org.example.wms.dto;

import lombok.*;
import org.example.wms.dto.general.BaseDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO extends BaseDTO {
    private String name;
    private String description;
    private String sku;
}
