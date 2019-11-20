package com.eliseev.app.services;

import com.eliseev.app.models.AbstractEntity;
import com.eliseev.app.repository.IDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractService<E extends AbstractEntity,
        R extends IDAO<E>> implements IService<E> {

    Logger logger = LoggerFactory.getLogger(AbstractService.class);

    protected Map<Long, E> entities =
            new HashMap<>();
    protected long idIndex = 3L;

    protected R dao;

    public AbstractService(R dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public List<E> list() {
        return new ArrayList<>(dao.findAll());
    }

    @Transactional
    @Override
    public E create(E entity) {
        return dao.save(entity);
    }

    @Transactional
    @Override
    public E get(long id) {
        E entity = dao.findOne(id);
        logger.info("found entity {} with id {}", entity, id);
        return entity;
    }

    @Transactional
    @Override
    public E update(long id, E entity) {
        entity.setId(id);
        return dao.save(entity);
    }

    @Transactional
    @Override
    public void delete(long id) {
         dao.delete(id);
    }


}
