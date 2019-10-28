package com.eliseev.app.controllers;

import com.eliseev.app.models.TrainStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/trainStations")
public class TrainStationsController extends AbstractController<TrainStation> {

    @Override
    public String findAll(Model model) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        List<TrainStation> trainStations = null;
        try {
            trainStations = Arrays.asList(
                    new TrainStation(1, "Минск", simpleDateFormat.parse("10.10.19 18:30"), simpleDateFormat.parse("10.10.19 18:35"),
                            1, 10,10,10),
                    new TrainStation(2, "Гродно", simpleDateFormat.parse("10.10.19 18:30"), simpleDateFormat.parse("10.10.19 18:35"),
                            2, 10,10,10),
                    new TrainStation(3, "Брест", simpleDateFormat.parse("10.10.19 18:30"), simpleDateFormat.parse("10.10.19 18:35"),
                            3, 10,10,10)
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("trainStations", trainStations);
        /*model.addAttribute("buatPage", page);*/
        model.addAttribute("station", new TrainStation());
        return "stations/stations";
    }

}
