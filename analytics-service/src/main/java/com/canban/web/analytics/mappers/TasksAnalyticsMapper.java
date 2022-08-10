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
                tasksAnalyticsDto.getTaskUserCreator(),
                tasksAnalyticsDto.getTaskUserExecutor(),
                tasksAnalyticsDto.getTaskBeginDate(),
                tasksAnalyticsDto.getTaskEndDate(),
                tasksAnalyticsDto.getTaskActualEndDate(),
                tasksAnalyticsDto.getTaskState(),
                tasksAnalyticsDto.getTaskPriority(),
                tasksAnalyticsDto.getKanbanBoardId()
                );
    }

    public TasksAnalyticsDto entityToDto(TasksAnalytics tasksAnalytics) {
        return new TasksAnalyticsDto(
                tasksAnalytics.getTaskId(),
                tasksAnalytics.getTaskTitle(),
                tasksAnalytics.getTaskUserCreator(),
                tasksAnalytics.getTaskUserExecutor(),
                tasksAnalytics.getTaskBeginDate(),
                tasksAnalytics.getTaskEndDate(),
                tasksAnalytics.getTaskActualEndDate(),
                tasksAnalytics.getTaskState(),
                tasksAnalytics.getTaskPriority(),
                tasksAnalytics.getTaskKanbanBoardId()
        );
    }

}
