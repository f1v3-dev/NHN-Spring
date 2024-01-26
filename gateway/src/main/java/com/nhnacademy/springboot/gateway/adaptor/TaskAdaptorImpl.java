package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.config.TaskAdaptorProperties;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.ProjectListRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.ProjectRegisterRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.TaskDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagResponseDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagRequestDto;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TaskAdaptorImpl implements TaskAdaptor {
    private final RestTemplate restTemplate;

    private final TaskAdaptorProperties taskAdaptorProperties;

    public TaskAdaptorImpl(RestTemplate restTemplate, TaskAdaptorProperties accountAdaptorProperties) {
        this.restTemplate = restTemplate;
        this.taskAdaptorProperties = accountAdaptorProperties;
    }

    @Override
    public List<TagResponseDto> getTagList(Long projectId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<TagResponseDto>> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/project/{id}/tag",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, projectId);

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException();
        }


        return exchange.getBody();
    }

    @Override
    public List<Milestone> getMilestoneList() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        return null;
    }

    @Override
    public List<TaskDto> getTaskList() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Object> requestEntity = new HttpEntity<>(httpHeaders);
        return null;
    }

    @Override
    public List<ProjectListRequestDto> getProjectList(String userId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<ProjectListRequestDto>> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/member/{id}/projects",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, userId);

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public CreateResponse registerProject(ProjectRegisterRequestDto project) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<ProjectRegisterRequestDto> requestEntity = new HttpEntity<>(project, httpHeaders);

        ResponseEntity<CreateResponse> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/project",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        if (HttpStatus.CREATED != exchange.getStatusCode()) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public CreateResponse registerTag(TagRequestDto tagRegisterDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<TagRequestDto> requestEntity = new HttpEntity<>(tagRegisterDto, httpHeaders);

        ResponseEntity<CreateResponse> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/tag",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (HttpStatus.CREATED != exchange.getStatusCode()) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public void deleteTag(Long tagId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/tag/{id}",
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, tagId);

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException();
        }

    }

    @Override
    public TagDto getTag(Long tagId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<TagDto> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/tag/{id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, tagId);

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public CreateResponse updateTag(Long projectId, TagRequestDto tagUpdateRequestDto) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<TagRequestDto> requestEntity = new HttpEntity<>(tagUpdateRequestDto, httpHeaders);

        ResponseEntity<CreateResponse> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/tag/{id}",
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, projectId);

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }
}
