package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.Station;
import com.eliseev.app.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/stations/list", produces = "application/json")
public class StationRestController extends AbstractRestController<Station, StationService> {

    @Autowired
    public StationRestController(StationService service) {
        super(service);
    }
}
