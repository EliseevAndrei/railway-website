package com.eliseev.app.services;

import com.eliseev.app.models.Route;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainRoutePiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RouteService {

    private TrainService trainService;
    private TrainRoutePieceService trainRoutePieceService;

    @Autowired
    public RouteService(TrainService trainService,
                        TrainRoutePieceService trainRoutePieceService) {
        this.trainService = trainService;
        this.trainRoutePieceService = trainRoutePieceService;
    }

    public List<Route> findRoutes(String depStation, String arrStation, Date date) {
        List<Route> routes = new ArrayList<>();
        List<Train> trains = trainService.list();
        Route route;
        TrainRoutePiece depSt;
        TrainRoutePiece arrSt;

        for(Train train : trains) {

            route = new Route();
            route.setTrainId(train.getId());
            route.setTrainName(train.getName());
            depSt = trainRoutePieceService.list(train.getId()).get(0);
            route.setDepStation(depSt.getStartStation().getName());
            //todo return
            /*route.setDepTime(depSt.getDepartureTime());
            route.setCoupe_places_amount(depSt.getCoupe_places_amount());
            route.setLying_places_amount(depSt.getLying_places_amount());
            route.setCommon_places_amount(depSt.getCommon_places_amount());
            arrSt = trainStationsService.list(train.getId()).get(1);
            route.setArrStation(arrSt.getStation().getName());
            route.setArrTime(arrSt.getArriveTime());*/

            routes.add(route);
        }

        return routes;
    }

}
