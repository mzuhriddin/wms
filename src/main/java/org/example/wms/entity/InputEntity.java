package org.example.wms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "input")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "inputEntity", cascade = CascadeType.ALL)
    private List<InputProductEntity> items;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WarehouseEntity warehouseEntity;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity userEntity;

    @CreationTimestamp
    private LocalDateTime createdDate;
}

