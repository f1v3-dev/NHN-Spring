package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.config.AccountAdaptorProperties;
import com.nhnacademy.springboot.gateway.domain.task.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.Tag;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TaskAdaptorImpl implements TaskAdaptor {
    private final RestTemplate restTemplate;

    private final AccountAdaptorProperties accountAdaptorProperties;

    public TaskAdaptorImpl(RestTemplate restTemplate, AccountAdaptorProperties accountAdaptorProperties) {
        this.restTemplate = restTemplate;
        this.accountAdaptorProperties = accountAdaptorProperties;
    }

    @Override
    public List<Tag> getTagList() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        return null;
    }

    @Override
    public List<Milestone> getMilestoneList() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        return null;
    }
}
