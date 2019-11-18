package com.eliseev.app.services;

import com.eliseev.app.models.AbstractEntity;

import java.util.List;

public interface IService<E extends AbstractEntity> {

    List<E> list();

    E create(E entity);

    E get(long id);

    E update(long id, E entity);

    void delete(long id);

}
