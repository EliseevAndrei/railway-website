package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.AbstractEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IRestController<E extends AbstractEntity> {

    @GetMapping
    List<E> list();

    @PostMapping
    E create(E entity, HttpServletResponse response);

    @GetMapping("/{id}")
    E findById(@PathVariable long id);

    @PutMapping("/{id}")
    E update(@PathVariable long id, E entity, HttpServletResponse response);

    @DeleteMapping("/{id}")
    E delete(@PathVariable long id, HttpServletResponse response);

}
