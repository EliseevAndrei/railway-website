package com.eliseev.app.service;

import com.eliseev.app.dto.StationDto;
import com.eliseev.app.dto.mapper.StationMapper;
import com.eliseev.app.models.Station;
import com.eliseev.app.repository.custom.StationDAO;
import com.eliseev.app.services.StationService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class StationServiceTest {

    @InjectMocks
    private StationService stationService;

    private ModelMapper modelMapper = new ModelMapper();

    @Spy
    private StationMapper stationMapper = new StationMapper(modelMapper);

    @Mock
    private StationDAO stationDAO;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findOne() {
        Station station = STATIONS[0];
        StationDto stationDto = stationMapper.toDto(station);
        Mockito.when(stationDAO.findOne(1, "")).thenReturn(station);
        assertEquals(stationDto, stationService.get(1L));
    }

    @Test
    public void list() {
        List<Station> stations = Arrays.stream(STATIONS).collect(Collectors.toList());
        Mockito.when(stationDAO.findAll("")).thenReturn(stations);

        List<StationDto> stationDtos = stationService.list();
        assertEquals(3, stationDtos.size());
    }



    private static Station[] STATIONS = new Station[3];

    @BeforeClass
    public static void before() {

        STATIONS[0] = new Station(1L, "Брест");
        STATIONS[1] = new Station(2L, "Минск");
        STATIONS[2] = new Station(3L, "Гродно");

    }


}
