package com.eliseev.app.controllers;

import com.eliseev.app.models.Siswa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class HttpMappingController {

    private final Logger logger = LoggerFactory.getLogger(HttpMappingController.class);

    @GetMapping("/")
    public String createIndex(Model model,
                              @RequestParam(defaultValue = "0", name = "page") int page) {
        List<Siswa> siswas = Arrays.asList(
                new Siswa(1, "andre", "eliseev"),
                new Siswa(2, "vera", "kozlovskaya"),
                new Siswa(3, "pasha", "kora")
        );
        model.addAttribute("siswas", siswas);
        model.addAttribute("buatPage", page);
        model.addAttribute("buatSiswa", new Siswa());
        return "stations/stations";
    }

    @PostMapping("/save")
    public String save(Siswa siswa) {
        logger.info("save siswa {}", siswa);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteSiswa(@RequestParam("id") int id) {
        logger.info("delete with id = {}", id);
        return "redirect:/";
    }

}
