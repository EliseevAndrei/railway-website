package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.AbstractEntity;
import com.eliseev.app.services.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public abstract class AbstractRestController<E extends AbstractEntity,
        S extends IService<E>> implements IRestController<E> {

    private Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

    protected S service;

    public AbstractRestController(S service) {
        this.service = service;
    }

    @Override
    public List<E> list() {
        logger.info("User send GET /<entities>/list");
        return service.list();
    }

    @Override
    public E create(@RequestBody  E entity, HttpServletResponse response) {
        logger.info("User send POST /<entities>/list with body {}", entity);
        return service.create(entity);
    }

    @Override
    public E findById(long id) {
        logger.info("User send GET /<entities>/list/{}", id);
        return service.get(id);
    }

    @Override
    public E update(long id, @RequestBody E entity, HttpServletResponse response) {
        logger.info("User send PUT /<entities>/list/{} with body {}", id, entity);
        return service.update(id, entity);
    }

    @Override
    public E delete(long id, HttpServletResponse response) {
        logger.info("User send DELETE /<entities>/list/{}", id);
        return service.delete(id);
    }

}
