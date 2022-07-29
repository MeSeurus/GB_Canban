package com.canban.web.core.services;


import com.canban.web.core.dto.TaskDetailsRq;
import com.canban.web.core.entities.Task;
import com.canban.web.core.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findTaskByUsername(String username) {
        return taskRepository.findTasksByUserName(username);
    }


    public void createTask(String username, TaskDetailsRq taskDetailsRq) {
        Task task = Task.taskBuilder()
                .title(taskDetailsRq.getTitle())
                .content(taskDetailsRq.getContent())
                .username(username)
                .beginDate(taskDetailsRq.getBeginDate())
                .endDate(taskDetailsRq.getEndDate())
                .actualEndDate(taskDetailsRq.getActualEndDate())
                .kanbanName(taskDetailsRq.getKanbanName())
                .state(taskDetailsRq.getState())
                .priority(taskDetailsRq.getPriority())

                .build();
        taskRepository.save(task);
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

}
