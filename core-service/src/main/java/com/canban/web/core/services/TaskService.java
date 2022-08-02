package com.canban.web.core.services;


import com.canban.web.core.dto.TaskDetailsRq;
import com.canban.web.core.entities.Event;
import com.canban.web.core.entities.Task;
import com.canban.web.core.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.PropertyValues;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    private List<Task> tasks = new CopyOnWriteArrayList<>();

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
        tasks.add(task);
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findAllForAnalytics() {
        return tasks;
    }

    public void clearList() {
        tasks.clear();
    }


}
