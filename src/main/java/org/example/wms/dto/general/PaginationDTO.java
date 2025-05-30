package org.example.wms.dto.general;

import lombok.Data;

@Data
public class PaginationDTO {
    private int page;
    private int size;
    private Boolean descending;
    private String sort;
}
