package com.db2Hibernate.proj.service;

import com.db2Hibernate.proj.JSONModels.Details;
import com.db2Hibernate.proj.JSONModels.Response;
import com.db2Hibernate.proj.base.RestServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@Service("sample1_endpoint")
public class RestService implements RestServiceBase {

    @Autowired
    RestTemplate restTemplate;

    private final String uri = "http://localhost:8086/external/endpoint";

    @Override
    public List<Details> getIds() {
        String finalUrl = uri + "/id";
        Response result = restTemplate.getForObject(finalUrl, Response.class);

        System.out.println(result.toString());
        List<Details> listOfIds = result.getData();
        return listOfIds;
    }

    @Override
    public List<Details> getNames() {

        String finalUrl = uri + "/lastName";
        Response result = restTemplate.getForObject(finalUrl, Response.class);

        List<Details> listOfIds = result.getData();
        return listOfIds;
    }



}
