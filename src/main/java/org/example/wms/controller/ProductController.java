package org.example.wms.controller;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.ProductDTO;
import org.example.wms.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.saveProduct(dto));
    }
}
