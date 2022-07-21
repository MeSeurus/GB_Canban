package com.canban.web.core.services;

import com.canban.web.core.dto.TaskDetailsRq;
import com.canban.web.core.entities.Event;
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
                .username(taskDetailsRq.getUsername())
                .eventDate(taskDetailsRq.getEventDate())
                .kanbanName(taskDetailsRq.getKanbanName())
                .build();
        taskRepository.save(task);
    }

    public List<Event> findAll() {
        return taskRepository.findAll();
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public void save(Task task) {
        taskRepository.save(task);
    }
}
