package com.canban.web.core.mapper;

import com.canban.api.core.TaskDto;
import com.canban.web.core.entities.Task;
import com.canban.api.core.Priority;
import com.canban.api.core.State;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDto entityToDtoWithCreator(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getContent(),
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

    public Task dtoToEntityWithCreator(TaskDto taskDto) {
        return Task.taskBuilder()
                .title(taskDto.getTitle())
                .content(taskDto.getContent())
                .userCreator(taskDto.getUserCreator())
                .userExecutor(taskDto.getUserExecutor())
                .beginDate(taskDto.getBeginDate())
                .endDate(taskDto.getEndDate())
                .actualEndDate(taskDto.getActualEndDate())
                .state(taskDto.getState())
                .priority(taskDto.getPriority())
                .kanbanBoardId(taskDto.getKanbanBoardId()).build();
    }

    public Task dtoToEntityWithoutCreator(TaskDto taskDto) {
        return Task.taskBuilder()
                .title(taskDto.getTitle())
                .content(taskDto.getContent())
                .userExecutor(taskDto.getUserExecutor())
                .beginDate(taskDto.getBeginDate())
                .endDate(taskDto.getEndDate())
                .actualEndDate(taskDto.getActualEndDate())
                .state(taskDto.getState())
                .priority(taskDto.getPriority())
                .kanbanBoardId(taskDto.getKanbanBoardId()).build();
    }
}