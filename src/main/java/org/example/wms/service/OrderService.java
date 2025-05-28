package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.InboundOrderDTO;
import org.example.wms.dto.OutboundOrderDTO;
import org.example.wms.mapper.InboundOrderMapper;
import org.example.wms.mapper.OutboundOrderMapper;
import org.example.wms.repository.InboundOrderRepository;
import org.example.wms.repository.OutboundOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final InboundOrderRepository inboundOrderRepository;
    private final OutboundOrderRepository outboundOrderRepository;
    private final InboundOrderMapper inboundOrderMapper;
    private final OutboundOrderMapper outboundOrderMapper;

    public List<InboundOrderDTO> getInboundOrders() {
        return inboundOrderRepository.findAll().stream()
                .map(inboundOrderMapper::toDTO)
                .toList();
    }

    public List<OutboundOrderDTO> getOutboundOrders() {
        return outboundOrderRepository.findAll().stream()
                .map(outboundOrderMapper::toDTO)
                .toList();
    }
}