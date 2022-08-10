package com.canban.web.analytics.mappers;

import com.canban.api.analytics.TaskAnalyticsDto;
import com.canban.web.analytics.entities.TaskAnalytics;
import org.springframework.stereotype.Component;

@Component
public class TaskAnalyticsMapper {

    public TaskAnalytics dtoToEntity(TaskAnalyticsDto taskAnalyticsDto) {
        return new TaskAnalytics(
                taskAnalyticsDto.getTaskId(),
                taskAnalyticsDto.getTaskTitle(),
                taskAnalyticsDto.getTaskUserCreator(),
                taskAnalyticsDto.getTaskUserExecutor(),
                taskAnalyticsDto.getTaskBeginDate(),
                taskAnalyticsDto.getTaskEndDate(),
                taskAnalyticsDto.getTaskActualEndDate(),
                taskAnalyticsDto.getTaskState(),
                taskAnalyticsDto.getTaskPriority(),
                taskAnalyticsDto.getKanbanBoardId()
        );
    }

    public TaskAnalyticsDto entityToDto(TaskAnalytics taskAnalytics) {
        return new TaskAnalyticsDto(
                taskAnalytics.getTaskId(),
                taskAnalytics.getTaskTitle(),
                taskAnalytics.getTaskUserCreator(),
                taskAnalytics.getTaskUserExecutor(),
                taskAnalytics.getTaskBeginDate(),
                taskAnalytics.getTaskEndDate(),
                taskAnalytics.getTaskActualEndDate(),
                taskAnalytics.getTaskState(),
                taskAnalytics.getTaskPriority(),
                taskAnalytics.getTaskKanbanBoardId()
        );
    }

}
