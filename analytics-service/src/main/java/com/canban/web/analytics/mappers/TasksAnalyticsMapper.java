package com.canban.web.analytics.mappers;

import com.canban.api.analytics.TasksAnalyticsDto;
import com.canban.web.analytics.entities.TasksAnalytics;
import org.springframework.stereotype.Component;

@Component
public class TasksAnalyticsMapper {

    public TasksAnalytics dtoToEntity(TasksAnalyticsDto tasksAnalyticsDto) {
        return new TasksAnalytics(
                tasksAnalyticsDto.getTaskId(),
                tasksAnalyticsDto.getTaskTitle(),
                tasksAnalyticsDto.getTaskUsername(),
                tasksAnalyticsDto.getTaskBeginDate(),
                tasksAnalyticsDto.getTaskEndDate(),
                tasksAnalyticsDto.getTaskActualEndDate(),
                tasksAnalyticsDto.getTaskState(),
                tasksAnalyticsDto.getTaskPriority(),
                tasksAnalyticsDto.getTaskKanbanName()
                );
    }

}
