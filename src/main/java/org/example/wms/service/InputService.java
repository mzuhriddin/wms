package org.example.wms.service;

import jakarta.transaction.Transactional;
import org.example.wms.dto.InputDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.entity.InputEntity;
import org.example.wms.entity.InventoryEntity;
import org.example.wms.mapper.InputMapper;
import org.example.wms.repository.InputRepository;
import org.example.wms.repository.InventoryRepository;
import org.example.wms.service.base.CRUDService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InputService extends CRUDService<InputRepository, InputEntity, InputDTO, InputMapper> {

    private final InventoryService inventoryService;
    private final InventoryRepository inventoryRepository;

    public InputService(InputRepository repository, InputMapper mapper, InventoryService inventoryService,
                           InventoryRepository inventoryRepository) {
        super(repository, mapper);
        this.inventoryService = inventoryService;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    @Transactional
    public ApiResponse<InputDTO> create(InputDTO dto) {
        InputEntity entity = mapper.toEntity(dto);
        entity.getItems().forEach(item -> item.setInputEntity(entity));
        InputEntity savedEntity = repository.save(entity);

        entity.getItems().forEach(item -> {
            Optional<InventoryEntity> optionalInventory = inventoryService.findByProductAndWarehouse(item.getProductEntity(), entity.getWarehouseEntity());
            if (optionalInventory.isPresent()) {
                InventoryEntity inventory = optionalInventory.get();
                inventory.setQuantity(inventory.getQuantity() + item.getQuantity());
                inventoryRepository.save(inventory);
            } else {
                InventoryEntity inventory = new InventoryEntity();
                inventory.setProductEntity(item.getProductEntity());
                inventory.setWarehouseEntity(entity.getWarehouseEntity());
                inventory.setQuantity(item.getQuantity());
                inventoryRepository.save(inventory);
            }
        });

        return new ApiResponse<>(200, "SUCCESS", mapper.toDTO(savedEntity), 0);
    }

    @Override
    public ApiResponse<InputDTO> update(Long id, InputDTO dto) {
        return new ApiResponse<>(405, "METHOD NOT ALLOWED", null, 0);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        return new ApiResponse<>(405, "METHOD NOT ALLOWED", null, 0);
    }
}
