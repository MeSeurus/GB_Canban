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
    public void createTask(String username, TaskDetailsRq taskDetails) {
        Task task = Task.taskBuilder()
                .title(taskDetails.getTitle())
                .content(taskDetails.getContent())
                .username(username)
                .beginDate(taskDetails.getBeginDate())
                .dueDate(taskDetails.getDueDate())
                .kanbanName(taskDetails.getKanbanName())
                .state(taskDetails.getState())
                .priority(taskDetails.getPriority())
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