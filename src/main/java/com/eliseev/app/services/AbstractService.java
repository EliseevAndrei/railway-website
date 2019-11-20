package com.eliseev.app.services;

import com.eliseev.app.models.AbstractEntity;
import com.eliseev.app.repository.IDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractService<E extends AbstractEntity,
        R extends IDAO<E>> implements IService<E> {

    protected Map<Long, E> entities =
            new HashMap<>();
    protected long idIndex = 3L;

    protected R dao;

    public AbstractService(R dao) {
        this.dao = dao;
    }

    @Override
    public List<E> list() {
        return new ArrayList<>(dao.findAll());
    }

    @Override
    public E create(E entity) {
        return dao.save(entity);
    }

    @Override
    public E get(long id) {
        return dao.findOne(id);
    }

    @Override
    public E update(long id, E entity) {
        entity.setId(id);
        dao.save(entity);
        return entity;
    }

    @Override
    public void delete(long id) {
         dao.delete(id);
    }


}
