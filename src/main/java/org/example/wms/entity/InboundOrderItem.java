package org.example.wms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inbound_order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inbound_id")
    private InboundOrder inboundOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity = 0;

}