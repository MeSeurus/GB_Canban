package com.canban.web.core.mapper;

import com.canban.api.core.EventDto;
import com.canban.api.core.TaskDto;
import com.canban.web.core.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task dtoToEntity(EventDto eventDto) {
        throw new UnsupportedOperationException();
    }

    public TaskDto entityToDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                task.getUsername(),
                task.getEventDate(),
                task.getDue_date(),
                task.getState(),
                task.getPriority(),
                task.getKanban_name()
        );
    }


}
