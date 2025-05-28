package org.example.wms.controller;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.InventoryDTO;
import org.example.wms.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventoryQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(inventoryService.updateInventoryQuantity(id, quantity));
    }
}