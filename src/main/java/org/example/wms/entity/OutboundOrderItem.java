package org.example.wms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "outbound_order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outboundItemId;

    @ManyToOne
    @JoinColumn(name = "outbound_id")
    private OutboundOrder outboundOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

}