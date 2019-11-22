package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.AbstractEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IRestController<E extends AbstractEntity> {

    @GetMapping
    List<E> list(@PathVariable(required = false) long ...id);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    E create(@Valid @RequestBody E entity, @PathVariable(required = false) long ...id);

    @GetMapping("/{id}")
    E findById(@PathVariable long id);

    @PutMapping(value = "/{id}", consumes = "application/json")
    E update(@Valid @RequestBody E entity, @PathVariable long id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable long id);

}
