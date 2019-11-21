package com.eliseev.app.repository;

import com.eliseev.app.models.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractDAO<E extends AbstractEntity>
        implements IDAO<E> {

    private Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<E> clazz;

    public AbstractDAO() { }

    public AbstractDAO(Class<E> clazz) {
        this.clazz = clazz;
    }


    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public E findOne(long id) {
        E entity =  entityManager.find(clazz, id);
        logger.info("found entity {}", entity);
        return entity;
    }

    @Override
    public E save(E entity) {
        if (entity.getId() == null)
            entityManager.persist(entity);
        else {
            if (entity.getId() == -1) {
                entity.setId(null);
            }
            return entityManager.merge(entity);
        }

        return entity;
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findOne(id));
    }

    @Override
    public List<E> findAll() {
        return entityManager.createQuery("select s from " + clazz.getName() + " s", clazz)
                .getResultList();
    }

}
