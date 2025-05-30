package org.example.wms.controller;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.InputDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.dto.general.PaginationDTO;
import org.example.wms.service.InputService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/input")
@RequiredArgsConstructor
public class InputController {
    private final InputService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InputDTO>>> getInputs(PaginationDTO pagination) {
        return ResponseEntity.ok(service.getAll(pagination));
    }
}
