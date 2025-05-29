package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.InboundOrderDTO;
import org.example.wms.dto.OutboundOrderDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.mapper.InboundOrderMapper;
import org.example.wms.mapper.OutboundOrderMapper;
import org.example.wms.repository.InboundOrderRepository;
import org.example.wms.repository.OutboundOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final InboundOrderRepository inboundRepository;
    private final OutboundOrderRepository outboundRepository;
    private final InboundOrderMapper inboundMapper;
    private final OutboundOrderMapper outboundMapper;

    public ApiResponse<List<InboundOrderDTO>> getInboundOrders() {
        return new ApiResponse<>(200, "SUCCESS", inboundRepository.findAll().stream()
                .map(inboundMapper::toDTO)
                .toList());
    }

    public ApiResponse<List<OutboundOrderDTO>> getOutboundOrders() {
        return new ApiResponse<>(200, "SUCCESS", outboundRepository.findAll().stream()
                .map(outboundMapper::toDTO)
                .toList());
    }
}