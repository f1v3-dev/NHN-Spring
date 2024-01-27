package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.config.TaskAdaptorProperties;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.TaskUser;
import com.nhnacademy.springboot.gateway.domain.task.commnet.CommentRequest;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneDto;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneRegisterDto;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectListRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectRegisterRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskListResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskRegisterDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class TaskAdaptorImpl implements TaskAdaptor {
    private final RestTemplate restTemplate;

    private final TaskAdaptorProperties taskAdaptorProperties;

    public TaskAdaptorImpl(RestTemplate restTemplate, TaskAdaptorProperties accountAdaptorProperties) {
        this.restTemplate = restTemplate;
        this.taskAdaptorProperties = accountAdaptorProperties;
    }

    @Override
    public List<TagListModuleResponse> getTagList(Long projectId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<TagListModuleResponse>> exchange = restTemplate.exchange(
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
    public List<MilestoneDto> getMilestoneList(Long projectId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<MilestoneDto>> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/project/{id}/milestone",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, projectId);

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException("마일스톤 조회 실패");
        }

        return exchange.getBody();
    }

    @Override
    public TaskListResponse getTaskList(Long projectId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<TaskListResponse> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/project/{id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, projectId);

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException();
        }

        log.info("exchange.getBody() : {}", exchange.getBody());

        return exchange.getBody();
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

    @Override
    public TaskUser matches(String userId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<TaskUser> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/login/{id}",
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
    public CreateResponse registerMilestone(MilestoneRegisterDto milestone) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<MilestoneRegisterDto> requestEntity = new HttpEntity<>(milestone, httpHeaders);

        ResponseEntity<CreateResponse> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/milestone",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (HttpStatus.CREATED != exchange.getStatusCode()) {
            throw new RuntimeException("마일스톤 등록 실패");
        }

        return exchange.getBody();
    }

    @Override
    public MilestoneDto getMilestone(Long milestoneId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<MilestoneDto> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/milestone/{id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, milestoneId);

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException("마일스톤 조회 실패");
        }

        return exchange.getBody();
    }

    @Override
    public CreateResponse updateMilestone(Long milestoneId, MilestoneRegisterDto milestone) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<MilestoneRegisterDto> requestEntity = new HttpEntity<>(milestone, httpHeaders);

        ResponseEntity<CreateResponse> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/milestone/{id}",
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, milestoneId);

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException("마일스톤 수정 실패");
        }

        return exchange.getBody();
    }

    @Override
    public CreateResponse registerTask(TaskRegisterDto task) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<TaskRegisterDto> requestEntity = new HttpEntity<>(task, httpHeaders);

        ResponseEntity<CreateResponse> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/task",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (HttpStatus.CREATED != exchange.getStatusCode()) {
            throw new RuntimeException("업무 등록 실패");
        }

        return exchange.getBody();
    }

    @Override
    public TaskModuleResponse getTask(Long taskId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<TaskModuleResponse> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/task/{id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, taskId);

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException("업무 조회 실패");
        }

        return exchange.getBody();
    }

    @Override
    public CreateResponse registerComment(Long taskId, CommentRequest comment) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<CommentRequest> requestEntity = new HttpEntity<>(comment, httpHeaders);

        ResponseEntity<CreateResponse> exchange = restTemplate.exchange(
                taskAdaptorProperties.getAddress() + "/task/{id}/comment",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, taskId);

        if (HttpStatus.CREATED != exchange.getStatusCode()) {
            throw new RuntimeException("댓글 등록 실패");
        }

        return exchange.getBody();
    }
}
