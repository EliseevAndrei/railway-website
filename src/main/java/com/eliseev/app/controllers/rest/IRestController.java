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
    List<E> list(@PathVariable(required = false) long ...id);

    @PostMapping
    E create(E entity, @PathVariable(required = false) long ...id);

    @GetMapping("/{id}")
    E findById(@PathVariable long id);

    @PutMapping("/{id}")
    E update(E entity, @PathVariable long id);

    @DeleteMapping("/{id}")
    E delete(@PathVariable long id);

}
