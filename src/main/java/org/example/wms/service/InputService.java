package org.example.wms.service;

import org.example.wms.dto.InputDTO;
import org.example.wms.entity.InputEntity;
import org.example.wms.mapper.InputMapper;
import org.example.wms.repository.InputRepository;
import org.example.wms.service.base.CRUDService;
import org.springframework.stereotype.Service;

@Service
public class InputService extends CRUDService<InputRepository, InputEntity, InputDTO, InputMapper> {
    protected InputService(InputRepository repository, InputMapper mapper) {
        super(repository, mapper);
    }
}
