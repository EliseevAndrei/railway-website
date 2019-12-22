package com.eliseev.app.repository;

import com.eliseev.app.models.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        try {
            Long object = (Long) entityManager.createQuery("select count(e.id) from " + clazz.getSimpleName() + " e")
                    .getSingleResult();
            return object;
        } catch (NoResultException e) {
            return 0;
        }

    }

    @Override
    public E findOne(long id) {
        E entity =  entityManager.find(clazz, id);
        logger.info("found entity {}", entity);
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E findOne(long id, String graphName) {

        Query query = entityManager.createQuery("select e from " + clazz.getSimpleName() + " e where e.id = :id", clazz)
                .setParameter("id", id);

        if (graphName.length() != 0) {
            EntityGraph graph = this.entityManager.getEntityGraph(graphName);
            query.setHint("javax.persistence.fetchgraph", graph);
        }

        try {
            return (E) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
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
        entityManager.remove(this.entityManager.getReference(clazz, id));
    }

    @Override
    public List<E> findAll() {
        return entityManager.createQuery("select s from " + clazz.getName() + " s", clazz)
                .getResultList();
    }

    @Override
    public List<E> findAll(String graphName) {
        Query query = entityManager.createQuery("select s from " + clazz.getName() + " s", clazz);
        if (graphName.length() != 0) {
            EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
            query.setHint("javax.persistence.fetchgraph", entityGraph);
        }
        return query.getResultList();
    }

}
