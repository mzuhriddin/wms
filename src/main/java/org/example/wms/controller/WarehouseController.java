package org.example.wms.controller;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.WarehouseDTO;
import org.example.wms.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
class WarehouseController {
    private final WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<WarehouseDTO>> getAllWarehouses() {
        return ResponseEntity.ok(warehouseService.getAllWarehouses());
    }

    @PostMapping
    public ResponseEntity<WarehouseDTO> createWarehouse(@RequestBody WarehouseDTO dto) {
        return ResponseEntity.ok(warehouseService.createWarehouse(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDTO> getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseDTO> updateWarehouse(@PathVariable Long id, @RequestBody WarehouseDTO dto) {
        return warehouseService.updateWarehouse(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        boolean deleted = warehouseService.deleteWarehouse(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

