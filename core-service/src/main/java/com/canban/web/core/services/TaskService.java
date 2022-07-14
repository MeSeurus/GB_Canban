package com.canban.web.core.services;

import com.canban.web.core.dto.EventDetails;
import com.canban.web.core.dto.TaskDetails;
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

    public List<Event> findTaskByUser(String username) {
        return taskRepository.findTasksByUserName(username);
    }

    public void createTask(String username, TaskDetails taskDetails) {
        Task task = Task.taskBuilder()
                .title(taskDetails.getTitle())
                .content(taskDetails.getContent())
                .userName(username)
                .eventDate(taskDetails.getEventDate())
                .kanbanName(taskDetails.getCanbanName())
                .build();
        taskRepository.save(task);
    }

    public List<Event> findAll() {
        return taskRepository.findAll();
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
