package com.eliseev.app.repository;

import com.eliseev.app.models.AbstractEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractDAO<E extends AbstractEntity>
        implements IDAO<E> {

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
        return entityManager.find(clazz, id);
    }

    @Override
    public E save(E entity) {
        if (entity.getId() == null)
            entityManager.persist(entity);
        else
            return entityManager.merge(entity);

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
