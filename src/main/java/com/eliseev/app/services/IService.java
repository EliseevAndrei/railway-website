package com.eliseev.app.services;

import com.eliseev.app.dto.AbstractDto;

import java.util.List;

public interface IService<D extends AbstractDto> {

    List<D> list();

    D create(D entity);

    D get(long id);

    D update(long id, D entity);

    void delete(long id);

}
