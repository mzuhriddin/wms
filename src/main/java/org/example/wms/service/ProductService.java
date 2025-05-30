package org.example.wms.service;

import org.example.wms.dto.ProductDTO;
import org.example.wms.entity.ProductEntity;
import org.example.wms.mapper.ProductMapper;
import org.example.wms.repository.ProductRepository;
import org.example.wms.service.base.CRUDService;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends CRUDService<ProductRepository, ProductEntity, ProductDTO, ProductMapper> {

    protected ProductService(ProductRepository repository, ProductMapper mapper) {
        super(repository, mapper);
    }
}
