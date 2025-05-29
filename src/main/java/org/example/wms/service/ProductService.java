package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.ProductDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.mapper.ProductMapper;
import org.example.wms.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ApiResponse<List<ProductDTO>> getAllProducts() {
        return new ApiResponse<>(200, "SUCCESS", repository.findAll().stream()
                .map(mapper::toDTO)
                .toList());
    }

    public ApiResponse<ProductDTO> saveProduct(ProductDTO dto) {
        return new ApiResponse<>(200, "SUCCESS", mapper.toDTO(repository.save(mapper.toEntity(dto))));
    }
}
