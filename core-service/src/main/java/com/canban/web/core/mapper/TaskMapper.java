package com.canban.web.core.mapper;

import com.canban.api.core.TaskDto;
import com.canban.web.core.entities.Task;
import com.canban.api.core.Priority;
import com.canban.api.core.State;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDto entityToDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                task.getUsername(),
                task.getBeginDate(),
                task.getEndDate(),
                task.getActualEndDate(),
                task.getState().toString(),
                task.getPriority().toString(),
                task.getKanbanBoardId()
        );
    }

    public Task dtoToEntity(TaskDto taskDto) {
        return Task.taskBuilder().title(taskDto.getTitle())
                .content(taskDto.getContent())
                .username(taskDto.getUsername())
                .beginDate(taskDto.getBeginDate())
                .endDate(taskDto.getEndDate())
                .actualEndDate(taskDto.getActualEndDate())
                .state(State.valueOf(taskDto.getState()))
                .priority(Priority.valueOf(taskDto.getPriority()))
                .kanbanBoardId(taskDto.getKanbanBoardId()).build();
    }
}