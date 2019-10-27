package com.eliseev.app.controllers;

import com.eliseev.app.models.Siswa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpMappingRestController {
    private final Logger logger = LoggerFactory.getLogger(HttpMappingRestController.class);
    @GetMapping("/findOne")
    public Siswa createFindOne(@RequestParam("id") Integer id) {
        Siswa s = new Siswa(0, "andrei", "vera");
        //Siswa tmp = siswaDao.getOne(id);
        //init
        /*s.setId(tmp.getId());
        s.setNama(tmp.getNama());
        s.setJurusan(tmp.getJurusan());*/
        logger.info("get siswa by id = {}", id);
        return s;
    }
}
