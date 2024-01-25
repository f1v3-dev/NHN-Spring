package com.nhnacademy.springboot.gateway.service;

import com.nhnacademy.springboot.gateway.adaptor.TaskAdaptor;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskAdaptor taskAdaptor;

    public TaskService(TaskAdaptor taskAdaptor) {
        this.taskAdaptor = taskAdaptor;
    }
}
