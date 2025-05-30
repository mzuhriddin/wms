package org.example.wms.repository;

import org.example.wms.entity.OutputEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRepository extends JpaRepository<OutputEntity, Long> {
}