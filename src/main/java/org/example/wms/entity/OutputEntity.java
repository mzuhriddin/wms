package org.example.wms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "output")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "outputEntity", cascade = CascadeType.ALL)
    private List<OutputProductEntity> items;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WarehouseEntity warehouseEntity;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity userEntity;

    @CreationTimestamp
    private LocalDateTime createdDate;

}