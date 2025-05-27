package org.example.wms.repository;

import org.example.wms.entity.OutboundOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboundOrderItemRepository extends JpaRepository<OutboundOrderItem, Long> {
}