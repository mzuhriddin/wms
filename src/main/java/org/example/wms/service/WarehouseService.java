package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.WarehouseDTO;
import org.example.wms.entity.Warehouse;
import org.example.wms.mapper.WarehouseMapper;
import org.example.wms.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(warehouseMapper::toDTO)
                .toList();
    }

    public WarehouseDTO createWarehouse(WarehouseDTO dto) {
        Warehouse warehouse = warehouseMapper.toEntity(dto);
        return warehouseMapper.toDTO(warehouseRepository.save(warehouse));
    }

    public Optional<WarehouseDTO> getWarehouseById(Long id) {
        return warehouseRepository.findById(id).map(warehouseMapper::toDTO);
    }

    public Optional<WarehouseDTO> updateWarehouse(Long id, WarehouseDTO dto) {
        return warehouseRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setAddress(dto.getAddress());
            Warehouse updated = warehouseRepository.save(existing);
            return warehouseMapper.toDTO(updated);
        });
    }

    public boolean deleteWarehouse(Long id) {
        if (warehouseRepository.existsById(id)) {
            warehouseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

