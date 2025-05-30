package org.example.wms.dto;

import lombok.*;
import org.example.wms.dto.general.BaseDTO;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    private String username;
    private String role;
    private LocalDateTime createdDate;
}
