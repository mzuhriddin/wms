package org.example.wms.repository;

import org.example.wms.entity.InboundOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboundOrderItemRepository extends JpaRepository<InboundOrderItem, Long> {
}