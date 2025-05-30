package org.example.wms.service;

import org.example.wms.dto.OutputDTO;
import org.example.wms.entity.OutputEntity;
import org.example.wms.mapper.OutputMapper;
import org.example.wms.repository.OutputRepository;
import org.example.wms.service.base.CRUDService;
import org.springframework.stereotype.Service;

@Service
public class OutputService extends CRUDService<OutputRepository, OutputEntity, OutputDTO, OutputMapper> {
    protected OutputService(OutputRepository repository, OutputMapper mapper) {
        super(repository, mapper);
    }
}