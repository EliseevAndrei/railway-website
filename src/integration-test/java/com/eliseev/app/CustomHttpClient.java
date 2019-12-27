package com.eliseev.app;

import com.eliseev.app.models.UserRoleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class CustomHttpClient {

    private ObjectMapper objectMapper = new ObjectMapper();
    private CloseableHttpClient admin;
    private HttpClient user;
    private HttpClient anonymous;

    {

        /*CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials  credentials
                = new UsernamePasswordCredentials("dron", "dron");

        provider.setCredentials(AuthScope.ANY, credentials);

        admin = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();*/

        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials("dron", "dron");

        provider.setCredentials(AuthScope.ANY, credentials);
        admin = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();

        provider = new BasicCredentialsProvider();
        credentials
                = new UsernamePasswordCredentials("vera", "vera");

        provider.setCredentials(AuthScope.ANY, credentials);
        user = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();

        anonymous = HttpClients.custom().build();
    }

    private Header JSON[] = {
            new BasicHeader("Content-type", "application/json"),
            new BasicHeader("Accept", "application/json")
    };

    public HttpResponse post(String url, Object dto, UserRoleEnum role) throws IOException {
        /*HttpClient client = getHttpClient(role);*/
        HttpPost postRequest = new HttpPost(url);
        postRequest.setHeaders(JSON);
        postRequest.setEntity(new StringEntity(objectMapper.writeValueAsString(dto), StandardCharsets.UTF_8));
        return admin.execute(postRequest);
    }

    public HttpResponse get(String url, UserRoleEnum role) throws IOException {
        HttpClient client = getHttpClient(role);
        HttpGet getRequest = new HttpGet(url);
        getRequest.setHeaders(JSON);
        return client.execute(getRequest);
    }

    public HttpResponse put(String url, Object dto, UserRoleEnum role) throws IOException {
        HttpClient client = getHttpClient(role);
        HttpPut putRequest = new HttpPut(url);
        putRequest.setHeaders(JSON);
        putRequest.setEntity(new StringEntity(objectMapper.writeValueAsString(dto), StandardCharsets.UTF_8));
        return client.execute(putRequest);
    }

    public HttpResponse delete(String url, UserRoleEnum role) throws IOException {
        HttpClient client = getHttpClient(role);
        HttpDelete deleteRequest = new HttpDelete(url);
        deleteRequest.setHeaders(JSON);
        return client.execute(deleteRequest);
    }

    private HttpClient getHttpClient(UserRoleEnum role) {
        HttpClient httpClient;
        switch (role) {
            case USER: httpClient = user; break;
            case ADMIN: httpClient = admin; break;
            default: httpClient = anonymous;
        }
        return httpClient;
    }
}
