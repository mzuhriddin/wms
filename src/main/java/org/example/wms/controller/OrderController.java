package org.example.wms.controller;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.InboundOrderDTO;
import org.example.wms.dto.OutboundOrderDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/inbound")
    public ResponseEntity<ApiResponse<List<InboundOrderDTO>>> getInboundOrders() {
        return ResponseEntity.ok(orderService.getInboundOrders());
    }

    @GetMapping("/outbound")
    public ResponseEntity<ApiResponse<List<OutboundOrderDTO>>> getOutboundOrders() {
        return ResponseEntity.ok(orderService.getOutboundOrders());
    }
}
