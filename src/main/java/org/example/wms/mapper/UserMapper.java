package org.example.wms.mapper;

import org.example.wms.dto.UserDTO;
import org.example.wms.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
