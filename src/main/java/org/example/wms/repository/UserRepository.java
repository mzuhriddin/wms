package org.example.wms.repository;

import org.example.wms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByIsActiveTrue();

    Optional<UserEntity> findByUsername(String username);
}
