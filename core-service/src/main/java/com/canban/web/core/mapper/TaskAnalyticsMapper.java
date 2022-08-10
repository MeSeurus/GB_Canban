package com.canban.web.core.mapper;

import com.canban.api.analytics.TaskAnalyticsDto;
import com.canban.web.core.entities.Task;
import com.canban.web.core.entities.TaskForAnalytics;
import org.springframework.stereotype.Component;

@Component
public class TaskAnalyticsMapper {

    public TaskAnalyticsDto entityToDto(TaskForAnalytics taskForAnalytics) {
        return new TaskAnalyticsDto(
                taskForAnalytics.getId(),
                taskForAnalytics.getTaskTitle(),
                taskForAnalytics.getTaskUserCreator(),
                taskForAnalytics.getTaskUserExecutor(),
                taskForAnalytics.getTaskBeginDate(),
                taskForAnalytics.getTaskEndDate(),
                taskForAnalytics.getTaskActualEndDate(),
                taskForAnalytics.getTaskState(),
                taskForAnalytics.getTaskPriority(),
                taskForAnalytics.getTaskKanbanBoardId()
        );
    }

    public TaskForAnalytics taskToTaskForAnalytics(Task task) {
        return new TaskForAnalytics(
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
