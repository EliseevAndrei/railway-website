package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.AbstractEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IRestController<E extends AbstractEntity> {

    @GetMapping
    List<E> list(@PathVariable(required = false) long ...id);

    @PostMapping(consumes = "application/json")
    E create(@RequestBody E entity, @PathVariable(required = false) long ...id);

    @GetMapping("/{id}")
    E findById(@PathVariable long id);

    @PutMapping(value = "/{id}", consumes = "application/json")
    E update(@RequestBody E entity, @PathVariable long id);

    @DeleteMapping("/{id}")
    E delete(@PathVariable long id);

}
