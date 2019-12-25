package com.eliseev.app;

import com.eliseev.app.models.Station;
import com.eliseev.app.models.UserRoleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfigITConfig.class)
@WebAppConfiguration*/
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
public class SomeIT {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CustomHttpClient httpClient = new CustomHttpClient();
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void categoryTest() throws IOException {

        jdbcTemplate.update("insert into station (name) values(?)",
                "asfdasdfsdf");


        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials("dron", "dron");

        provider.setCredentials(AuthScope.ANY, credentials);

        CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();

        HttpResponse response = client.execute(
                new HttpGet("http://localhost:8080/stations/list"));
        int statusCode = response.getStatusLine()
                .getStatusCode();

        Assert.assertEquals(200, statusCode);

        HttpPost httpPost = new HttpPost("http://localhost:8080/stations/list");

        HttpResponse httpResponse =
                httpClient.post("http://localhost:8080/stations/list",
                        new Station(1, "12341234"),
                        UserRoleEnum.ADMIN);

        Station createdStation = mapper.readValue(getContent(httpResponse), Station.class);

        Long id = createdStation.getId();

        System.out.println(id);
        /*httpClient.post()*/
        /*CloseableHttpResponse closeableHttpResponse =
                client.execute(new HttpGet("http://localhost:8080/stations/list"));
        assertEquals(closeableHttpResponse.getStatusLine().getStatusCode(), 301);*/


        /*for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }*/
    }


    private String getContent(HttpResponse response) throws IOException {
        return IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
    }

}
