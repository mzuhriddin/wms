package org.example.wms.service;

import org.example.wms.dto.WarehouseDTO;
import org.example.wms.entity.WarehouseEntity;
import org.example.wms.mapper.WarehouseMapper;
import org.example.wms.repository.WarehouseRepository;
import org.example.wms.service.base.CRUDService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService extends CRUDService<WarehouseRepository, WarehouseEntity, WarehouseDTO, WarehouseMapper> {

    protected WarehouseService(WarehouseRepository repository, WarehouseMapper mapper) {
        super(repository, mapper);
    }
}

