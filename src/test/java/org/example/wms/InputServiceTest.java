package org.example.wms;

import org.example.wms.dto.InputDTO;
import org.example.wms.dto.InputProductDTO;
import org.example.wms.entity.InputEntity;
import org.example.wms.entity.InputProductEntity;
import org.example.wms.entity.InventoryEntity;
import org.example.wms.entity.ProductEntity;
import org.example.wms.entity.WarehouseEntity;
import org.example.wms.mapper.InputMapper;
import org.example.wms.repository.InputRepository;
import org.example.wms.repository.InventoryRepository;
import org.example.wms.service.InputService;
import org.example.wms.service.InventoryService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InputServiceTest {

    @Mock
    private InputRepository inputRepository;
    @Mock
    private InputMapper inputMapper;
    @Mock
    private InventoryService inventoryService;
    @Mock
    private InventoryRepository inventoryRepository;

    private InputService inputService;

    @BeforeEach
    void setUp() {
        inputService = new InputService(inputRepository, inputMapper, inventoryService, inventoryRepository);
    }

    @Test
    void testCreateInput_newInventory() {
        // Given
        InputDTO inputDTO = InputDTO.builder().warehouseId(1L).userId(1L)
                .items(Collections.singletonList(InputProductDTO.builder().productId(1L).quantity(10).build())).build();

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setId(1L);

        InputProductEntity inputProductEntity = new InputProductEntity();
        inputProductEntity.setProductEntity(productEntity);
        inputProductEntity.setQuantity(10);

        InputEntity inputEntity = new InputEntity();
        inputEntity.setWarehouseEntity(warehouseEntity);
        inputEntity.setItems(Collections.singletonList(inputProductEntity));

        when(inputMapper.toEntity(any(InputDTO.class))).thenReturn(inputEntity);
        when(inputRepository.save(any(InputEntity.class))).thenReturn(inputEntity);
        when(inventoryService.findByProductAndWarehouse(any(ProductEntity.class), any(WarehouseEntity.class)))
                .thenReturn(Optional.empty());

        // When
        inputService.create(inputDTO);

        // Then
        ArgumentCaptor<InventoryEntity> inventoryCaptor = ArgumentCaptor.forClass(InventoryEntity.class);
        verify(inventoryRepository).save(inventoryCaptor.capture());
        InventoryEntity savedInventory = inventoryCaptor.getValue();

        assertEquals(10, savedInventory.getQuantity());
        assertEquals(1L, savedInventory.getProductEntity().getId());
        assertEquals(1L, savedInventory.getWarehouseEntity().getId());
    }

    @Test
    void testCreateInput_existingInventory() {
        // Given
        InputDTO inputDTO = InputDTO.builder().warehouseId(1L).userId(1L)
                .items(Collections.singletonList(InputProductDTO.builder().productId(1L).quantity(10).build())).build();

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setId(1L);

        InputProductEntity inputProductEntity = new InputProductEntity();
        inputProductEntity.setProductEntity(productEntity);
        inputProductEntity.setQuantity(10);

        InputEntity inputEntity = new InputEntity();
        inputEntity.setWarehouseEntity(warehouseEntity);
        inputEntity.setItems(Collections.singletonList(inputProductEntity));

        InventoryEntity existingInventory = new InventoryEntity();
        existingInventory.setQuantity(5);
        existingInventory.setProductEntity(productEntity);
        existingInventory.setWarehouseEntity(warehouseEntity);

        when(inputMapper.toEntity(any(InputDTO.class))).thenReturn(inputEntity);
        when(inputRepository.save(any(InputEntity.class))).thenReturn(inputEntity);
        when(inventoryService.findByProductAndWarehouse(any(ProductEntity.class), any(WarehouseEntity.class)))
                .thenReturn(Optional.of(existingInventory));

        // When
        inputService.create(inputDTO);

        // Then
        ArgumentCaptor<InventoryEntity> inventoryCaptor = ArgumentCaptor.forClass(InventoryEntity.class);
        verify(inventoryRepository).save(inventoryCaptor.capture());
        InventoryEntity savedInventory = inventoryCaptor.getValue();

        assertEquals(15, savedInventory.getQuantity());
    }
}
