package org.example.wms.controller;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.InventoryDTO;
import org.example.wms.dto.general.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<InventoryDTO>>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryDTO>> getInventoryById(@PathVariable Long id) {
        ApiResponse<InventoryDTO> resp = inventoryService.getInventoryById(id);
        return ResponseEntity.status(resp.getCode()).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryDTO>> updateInventoryQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        ApiResponse<InventoryDTO> resp = inventoryService.updateInventoryQuantity(id, quantity);
        return ResponseEntity.status(resp.getCode()).body(resp);
    }
}