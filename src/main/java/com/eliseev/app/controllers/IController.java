package com.eliseev.app.controllers;

import com.eliseev.app.models.AbstractEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IController<E extends AbstractEntity> {

    @GetMapping
    String read(Model model,
                @RequestParam(defaultValue = "0", name = "page") int page);

    @PostMapping("/save")
    String create(E abstractEntity, Model model);

    @PostMapping("/delete")
    String delete(@RequestParam("id") long id, Model model);

    @PostMapping("/edit")
    String update(@RequestParam("id") long id, Model model);

    String findAll(Model model);

}
