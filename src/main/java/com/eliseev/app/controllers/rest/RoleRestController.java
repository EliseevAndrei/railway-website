package com.eliseev.app.controllers.rest;

import com.eliseev.app.dto.RoleDto;
import com.eliseev.app.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/roles/list", produces = "application/json")
@Slf4j
public class RoleRestController extends AbstractRestController<RoleDto, RoleService> {

    @Autowired
    public RoleRestController(RoleService service) {
        super(service);
    }

}