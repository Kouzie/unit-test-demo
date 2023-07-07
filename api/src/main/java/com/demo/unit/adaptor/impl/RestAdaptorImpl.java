package com.demo.unit.adaptor.impl;

import com.demo.unit.adaptor.RestAdaptor;
import com.demo.unit.adaptor.dto.RestUserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Component
public class RestAdaptorImpl implements RestAdaptor {

    private final RestTemplate restTemplate;

    public RestAdaptorImpl(@Autowired RestTemplateBuilder builder) {
        restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory("https://jsonplaceholder.typicode.com"))
                .build();
    }

    public RestUserPost findPostById(Long id) {
        RestUserPost result = restTemplate.getForObject("/posts/{id}", RestUserPost.class, id);
        return result;
    }
}
