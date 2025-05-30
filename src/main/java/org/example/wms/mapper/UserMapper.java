package org.example.wms.mapper;

import org.example.wms.dto.UserDTO;
import org.example.wms.entity.UserEntity;
import org.example.wms.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserEntity, UserDTO> {
    @Override
    default UserDTO toDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setRole(userEntity.getRole());
        userDTO.setCreatedDate(userEntity.getCreatedDate());
        return userDTO;
    }
}
