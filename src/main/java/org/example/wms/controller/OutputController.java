package org.example.wms.controller;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.OutputDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.dto.general.PaginationDTO;
import org.example.wms.service.OutputService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/output")
@RequiredArgsConstructor
public class OutputController {
    private final OutputService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OutputDTO>>> getOutputs(PaginationDTO pagination) {
        return ResponseEntity.ok(service.getAll(pagination));
    }
}
