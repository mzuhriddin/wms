package org.example.wms.service;

import org.example.wms.dto.UserDTO;
import org.example.wms.entity.UserEntity;
import org.example.wms.mapper.UserMapper;
import org.example.wms.repository.UserRepository;
import org.example.wms.service.base.CRUDService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CRUDService<UserRepository, UserEntity, UserDTO, UserMapper> {

    protected UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }
}