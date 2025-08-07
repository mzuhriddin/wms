package org.example.wms.service;

import jakarta.transaction.Transactional;
import org.example.wms.dto.OutputDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.entity.InventoryEntity;
import org.example.wms.entity.OutputEntity;
import org.example.wms.mapper.OutputMapper;
import org.example.wms.repository.InventoryRepository;
import org.example.wms.repository.OutputRepository;
import org.example.wms.service.base.CRUDService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutputService extends CRUDService<OutputRepository, OutputEntity, OutputDTO, OutputMapper> {

    private final InventoryService inventoryService;
    private final InventoryRepository inventoryRepository;

    public OutputService(OutputRepository repository, OutputMapper mapper, InventoryService inventoryService,
                            InventoryRepository inventoryRepository) {
        super(repository, mapper);
        this.inventoryService = inventoryService;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    @Transactional
    public ApiResponse<OutputDTO> create(OutputDTO dto) {
        OutputEntity entity = mapper.toEntity(dto);

        // Check for sufficient inventory
        for (var item : entity.getItems()) {
            Optional<InventoryEntity> optionalInventory = inventoryService.findByProductAndWarehouse(item.getProductEntity(), entity.getWarehouseEntity());
            if (optionalInventory.isEmpty() || optionalInventory.get().getQuantity() < item.getQuantity()) {
                return new ApiResponse<>(400, "INSUFFICIENT_INVENTORY", null, 0);
            }
        }

        entity.getItems().forEach(item -> item.setOutputEntity(entity));
        OutputEntity savedEntity = repository.save(entity);

        // Decrease inventory
        entity.getItems().forEach(item -> {
            Optional<InventoryEntity> optionalInventory = inventoryService.findByProductAndWarehouse(item.getProductEntity(), entity.getWarehouseEntity());
            if (optionalInventory.isPresent()) {
                InventoryEntity inventory = optionalInventory.get();
                inventory.setQuantity(inventory.getQuantity() - item.getQuantity());
                inventoryRepository.save(inventory);
            }
        });

        return new ApiResponse<>(200, "SUCCESS", mapper.toDTO(savedEntity), 0);
    }

    @Override
    public ApiResponse<OutputDTO> update(Long id, OutputDTO dto) {
        return new ApiResponse<>(405, "METHOD NOT ALLOWED", null, 0);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        return new ApiResponse<>(405, "METHOD NOT ALLOWED", null, 0);
    }
}