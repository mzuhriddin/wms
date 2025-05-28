package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.ProductDTO;
import org.example.wms.entity.Product;
import org.example.wms.mapper.ProductMapper;
import org.example.wms.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .toList();
    }

    public ProductDTO saveProduct(ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        return productMapper.toDTO(productRepository.save(product));
    }
}
