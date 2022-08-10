package com.canban.web.core.validators;

import com.canban.api.core.State;
import com.canban.api.core.TaskDto;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.api.exceptions.ValidationException;
import com.canban.web.core.dto.EventDetailsRq;
import com.canban.web.core.dto.TaskDetailsRq;
import com.canban.web.core.entities.Task;
import com.canban.web.core.repositories.TaskRepository;
import com.canban.web.core.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskValidator {

    private final TaskService taskService;

    public void validate(TaskDetailsRq taskDetailsRq) {
        List<String> errors = new ArrayList<>();
        if (taskDetailsRq.getTitle() != null && taskDetailsRq.getTitle().isBlank()) {
            errors.add("Title  must be filled in.");
        }
        if (taskDetailsRq.getKanbanBoardId() == null || taskDetailsRq.getKanbanBoardId().equals(0L)) {
            errors.add("The work space must be filled in.");
        }
        if (taskDetailsRq.getBeginDate() == null) {
            errors.add("Begin date must be filled in.");
        }
        if (taskDetailsRq.getEndDate() == null) {
            errors.add("End date must be filled in.");
        }
        if (taskDetailsRq.getBeginDate().compareTo(taskDetailsRq.getEndDate()) >= 0) {
            errors.add("The begin date of the task must be earlier than the end date.");
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.compareTo(taskDetailsRq.getEndDate()) >= 0) {
            errors.add("The task completion date must be later than the current time.");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public void validateUser(Long id) {
        List<String> errors = new ArrayList<>();
        Task task = taskService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change task's username."));
        if (task.getState() != State.CREATED) {
            errors.add("ERROR. The performer can be changed only in the CREATED state");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}