package org.example.wms.mapper.base;

public interface BaseMapper<E, D> {
    E toEntity(D d);
    D toDTO(E e);
}
