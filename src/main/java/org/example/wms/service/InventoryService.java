package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.InventoryDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.mapper.InventoryMapper;
import org.example.wms.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;
    private final InventoryMapper mapper;

    public ApiResponse<List<InventoryDTO>> getAllInventory() {
        return new ApiResponse<>(200, "SUCCESS", repository.findAll().stream()
                .map(mapper::toDTO)
                .toList());
    }

    public ApiResponse<InventoryDTO> getInventoryById(Long id) {
        return repository.findById(id).map(mapper::toDTO)
                .map(inventoryDTO -> new ApiResponse<>(200, "SUCCESS", inventoryDTO))
                .orElseGet(() -> new ApiResponse<>(404, "NOT FOUND"));
    }

    public ApiResponse<InventoryDTO> updateInventoryQuantity(Long id, Integer quantity) {
        Optional<InventoryDTO> optional = repository.findById(id).map(exiting -> {
            exiting.setQuantity(quantity);
            exiting.setLastUpdated(LocalDateTime.now());
            return mapper.toDTO(repository.save(exiting));
        });
        return optional.map(dto -> new ApiResponse<>(200, "SUCCESS", dto))
                .orElseGet(() -> new ApiResponse<>(404, "NOT FOUND"));
    }
}
