package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.InventoryDTO;
import org.example.wms.entity.Inventory;
import org.example.wms.mapper.InventoryMapper;
import org.example.wms.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public List<InventoryDTO> getAllInventory() {
        return inventoryRepository.findAll().stream()
                .map(inventoryMapper::toDTO)
                .toList();
    }

    public Optional<InventoryDTO> getInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .map(inventoryMapper::toDTO);
    }

    public InventoryDTO updateInventoryQuantity(Long id, Integer quantity) {
        Inventory inv = inventoryRepository.findById(id).orElseThrow();
        inv.setQuantity(quantity);
        inv.setLastUpdated(LocalDateTime.now());
        return inventoryMapper.toDTO(inventoryRepository.save(inv));
    }
}
