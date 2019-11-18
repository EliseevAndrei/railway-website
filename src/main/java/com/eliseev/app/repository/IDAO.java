package com.eliseev.app.repository;

import com.eliseev.app.models.AbstractEntity;

import java.util.List;


public interface IDAO<E extends AbstractEntity> {

    long count();

    E findOne(long id);

    E save(E entity);

    void delete(long id);

    List<E> findAll();

}
