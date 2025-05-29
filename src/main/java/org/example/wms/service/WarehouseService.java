package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.WarehouseDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.entity.Warehouse;
import org.example.wms.mapper.WarehouseMapper;
import org.example.wms.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository repository;
    private final WarehouseMapper mapper;

    public ApiResponse<List<WarehouseDTO>> getAllWarehouses() {
        return new ApiResponse<>(200, "SUCCESS", repository.findAll().stream()
                .map(mapper::toDTO)
                .toList());
    }

    public ApiResponse<WarehouseDTO> createWarehouse(WarehouseDTO dto) {
        return new ApiResponse<>(200, "SUCCESS", mapper.toDTO(repository.save(mapper.toEntity(dto))));
    }

    public ApiResponse<WarehouseDTO> getWarehouseById(Long id) {
        Optional<WarehouseDTO> optional = repository.findById(id).map(mapper::toDTO);
        return optional.map(warehouseDTO -> new ApiResponse<>(200, "SUCCESS", warehouseDTO))
                .orElseGet(() -> new ApiResponse<>(404, "NOT FOUND"));
    }

    public ApiResponse<WarehouseDTO> updateWarehouse(Long id, WarehouseDTO dto) {
        Optional<WarehouseDTO> optional = repository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setAddress(dto.getAddress());
            Warehouse updated = repository.save(existing);
            return mapper.toDTO(updated);
        });
        return optional.map(warehouseDTO -> new ApiResponse<>(200, "SUCCESS", warehouseDTO))
                .orElseGet(() -> new ApiResponse<>(404, "NOT FOUND"));
    }

    public ApiResponse<Void> deleteWarehouse(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ApiResponse<>(200, "SUCCESS");
        }
        return new ApiResponse<>(404, "NOT FOUND");
    }
}

