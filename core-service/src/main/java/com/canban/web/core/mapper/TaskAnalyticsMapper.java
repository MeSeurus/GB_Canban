package com.canban.web.core.mapper;

import com.canban.api.analytics.TasksAnalyticsDto;
import com.canban.web.core.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskAnalyticsMapper {

    public TasksAnalyticsDto entityToDto(Task task) {
        return new TasksAnalyticsDto(
                task.getId(),
                task.getTitle(),
                task.getUserCreator(),
                task.getUserExecutor(),
                task.getBeginDate(),
                task.getEndDate(),
                task.getActualEndDate(),
                task.getState(),
                task.getPriority(),
                task.getKanbanBoardId()
        );
    }

}
