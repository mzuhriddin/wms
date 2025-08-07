package org.example.wms;

import org.example.wms.dto.OutputDTO;
import org.example.wms.dto.OutputProductDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.entity.InventoryEntity;
import org.example.wms.entity.OutputEntity;
import org.example.wms.entity.OutputProductEntity;
import org.example.wms.entity.ProductEntity;
import org.example.wms.entity.WarehouseEntity;
import org.example.wms.mapper.OutputMapper;
import org.example.wms.repository.InventoryRepository;
import org.example.wms.repository.OutputRepository;
import org.example.wms.service.InventoryService;
import org.example.wms.service.OutputService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OutputServiceTest {

    @Mock
    private OutputRepository outputRepository;
    @Mock
    private OutputMapper outputMapper;
    @Mock
    private InventoryService inventoryService;
    @Mock
    private InventoryRepository inventoryRepository;

    private OutputService outputService;

    @BeforeEach
    void setUp() {
        outputService = new OutputService(outputRepository, outputMapper, inventoryService, inventoryRepository);
    }

    @Test
    void testCreateOutput_sufficientInventory() {
        // Given
        OutputDTO outputDTO = OutputDTO.builder().warehouseId(1L).userId(1L)
                .items(Collections.singletonList(OutputProductDTO.builder().productId(1L).quantity(5).build())).build();

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setId(1L);

        OutputProductEntity outputProductEntity = new OutputProductEntity();
        outputProductEntity.setProductEntity(productEntity);
        outputProductEntity.setQuantity(5);

        OutputEntity outputEntity = new OutputEntity();
        outputEntity.setWarehouseEntity(warehouseEntity);
        outputEntity.setItems(Collections.singletonList(outputProductEntity));

        InventoryEntity existingInventory = new InventoryEntity();
        existingInventory.setQuantity(10);
        existingInventory.setProductEntity(productEntity);
        existingInventory.setWarehouseEntity(warehouseEntity);

        when(outputMapper.toEntity(any(OutputDTO.class))).thenReturn(outputEntity);
        when(outputRepository.save(any(OutputEntity.class))).thenReturn(outputEntity);
        when(inventoryService.findByProductAndWarehouse(any(ProductEntity.class), any(WarehouseEntity.class)))
                .thenReturn(Optional.of(existingInventory));

        // When
        outputService.create(outputDTO);

        // Then
        ArgumentCaptor<InventoryEntity> inventoryCaptor = ArgumentCaptor.forClass(InventoryEntity.class);
        verify(inventoryRepository).save(inventoryCaptor.capture());
        InventoryEntity savedInventory = inventoryCaptor.getValue();

        assertEquals(5, savedInventory.getQuantity());
    }

    @Test
    void testCreateOutput_insufficientInventory() {
        // Given
        OutputDTO outputDTO = OutputDTO.builder().warehouseId(1L).userId(1L)
                .items(Collections.singletonList(OutputProductDTO.builder().productId(1L).quantity(15).build())).build();

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setId(1L);

        OutputProductEntity outputProductEntity = new OutputProductEntity();
        outputProductEntity.setProductEntity(productEntity);
        outputProductEntity.setQuantity(15);

        OutputEntity outputEntity = new OutputEntity();
        outputEntity.setWarehouseEntity(warehouseEntity);
        outputEntity.setItems(Collections.singletonList(outputProductEntity));

        InventoryEntity existingInventory = new InventoryEntity();
        existingInventory.setQuantity(10);
        existingInventory.setProductEntity(productEntity);
        existingInventory.setWarehouseEntity(warehouseEntity);

        when(outputMapper.toEntity(any(OutputDTO.class))).thenReturn(outputEntity);
        when(inventoryService.findByProductAndWarehouse(any(ProductEntity.class), any(WarehouseEntity.class)))
                .thenReturn(Optional.of(existingInventory));

        // When
        ApiResponse<OutputDTO> response = outputService.create(outputDTO);

        // Then
        assertEquals(400, response.getCode());
        assertEquals("INSUFFICIENT_INVENTORY", response.getMessage());
        verify(inventoryRepository, never()).save(any(InventoryEntity.class));
    }
}
