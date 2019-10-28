package com.eliseev.app.controllers;

import com.eliseev.app.models.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


public abstract class AbstractController<E extends AbstractEntity>
    implements  IController<E> {

    private final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    @Override
    public String read(Model model,
                       @RequestParam(defaultValue = "0", name = "page") int page) {
        logger.info("read abstract entities list");
        return findAll(model);
    }

    @Override
    public String create(E abstractEntity, Model model) {
        logger.info("create {}", abstractEntity);
        return findAll(model);
    }

    @Override
    public String delete(long id, Model model) {
        logger.info("delete entity with id {}", id);
        return findAll(model);
    }

    @Override
    public String update(long id, Model model) {
        logger.info("update entity with id {}", id);
        return findAll(model);
    }
    
}
