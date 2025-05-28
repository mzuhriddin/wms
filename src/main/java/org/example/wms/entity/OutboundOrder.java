package org.example.wms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "outbound_orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "outboundOrder", cascade = CascadeType.ALL)
    private List<OutboundOrderItem> items;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User user;

    private LocalDateTime createdAt = LocalDateTime.now();

}