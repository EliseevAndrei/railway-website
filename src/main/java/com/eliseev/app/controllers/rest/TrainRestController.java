package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.Train;
import com.eliseev.app.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/trains/list", produces = "application/json")
public class TrainRestController extends AbstractRestController<Train, TrainService> {

    @Autowired
    public TrainRestController(TrainService service) {
        super(service);
    }

}
