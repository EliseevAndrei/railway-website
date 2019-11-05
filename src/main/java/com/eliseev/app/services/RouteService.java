package com.eliseev.app.services;

import com.eliseev.app.models.Route;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainStation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RouteService {

    private TrainService trainService;
    private TrainStationsService trainStationsService;

    public RouteService(TrainService trainService,
                        TrainStationsService trainStationsService) {
        this.trainService = trainService;
        this.trainStationsService = trainStationsService;
    }

    public List<Route> findRoutes(String depStation, String arrStation, Date date) {
        List<Route> routes = new ArrayList<>();
        List<Train> trains = trainService.list();
        Route route;
        for(Train train : trains) {
            route = new Route(train,
                    trainStationsService.list(train.getId()).get(0),
                    trainStationsService.list(train.getId()).get(1));
            routes.add(route);
        }

        return routes;
    }

}
