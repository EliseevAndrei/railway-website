package com.eliseev.app;

import com.eliseev.app.dto.StationDto;
import com.eliseev.app.models.UserRoleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
public class StationIT {

    private static String URL = "http://localhost:8080/stations/list";

    @Autowired
    private DatabasePopulater databasePopulater;

    private CustomHttpClient httpClient = new CustomHttpClient();
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void populateDb() {
        databasePopulater.populateDb();
    }

    @After
    public void deleteDb() {
        databasePopulater.cleanDb();
    }


    @Test
    public void categoryTest() throws IOException {

        HttpResponse post = httpClient.post(URL, new StationDto("newStation"), UserRoleEnum.ADMIN);
        StationDto createdBook = mapper.readValue(getContent(post),StationDto.class);

        assertEquals("newStation", createdBook.getName());

        HttpResponse getResponse = httpClient.get(URL, UserRoleEnum.ADMIN);
        List<StationDto> stations = mapper.readValue(getContent(getResponse), List.class);

        assertEquals(22, stations.size());

        StationDto forUpdateStation = new StationDto(1L, "updatedStation");
        HttpResponse updateResponse = httpClient.put(URL + "/" + 1L, forUpdateStation, UserRoleEnum.ADMIN);
        StationDto updatedStation = mapper.readValue(getContent(updateResponse), StationDto.class);
        assertEquals(forUpdateStation, updatedStation);

        HttpResponse deleteResponse = httpClient.delete(URL + "/" + 1L, UserRoleEnum.ADMIN);
        assertEquals(200, deleteResponse.getStatusLine().getStatusCode());
    }


    private String getContent(HttpResponse response) throws IOException {
        return IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
    }

}