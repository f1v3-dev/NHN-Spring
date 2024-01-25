package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.domain.task.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.Tag;
import java.util.List;

public interface TaskAdaptor {

    List<Tag> getTagList();

    List<Milestone> getMilestoneList();
}
