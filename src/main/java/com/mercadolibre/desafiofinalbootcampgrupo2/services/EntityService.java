package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import org.springframework.transaction.annotation.Transactional;

public interface EntityService<T> {

    @Transactional(readOnly = true)
    T findById(Long id);
}
