package org.example.wms.controller;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.WarehouseDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.dto.general.PaginationDTO;
import org.example.wms.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("warehouses")
@RequiredArgsConstructor
class WarehouseController {
    private final WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<WarehouseDTO>>> getAllWarehouses(PaginationDTO pagination) {
        return ResponseEntity.ok(warehouseService.getAll(pagination));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WarehouseDTO>> createWarehouse(@RequestBody WarehouseDTO dto) {
        return ResponseEntity.ok(warehouseService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WarehouseDTO>> getWarehouseById(@PathVariable Long id) {
        ApiResponse<WarehouseDTO> response = warehouseService.getById(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WarehouseDTO>> updateWarehouse(@PathVariable Long id, @RequestBody WarehouseDTO dto) {
        ApiResponse<WarehouseDTO> response = warehouseService.update(id, dto);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteWarehouse(@PathVariable Long id) {
        ApiResponse<Void> response = warehouseService.delete(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}

