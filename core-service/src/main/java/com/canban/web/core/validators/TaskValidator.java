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
            errors.add("Название задачи должно быть указано.");
        }
        if (taskDetailsRq.getKanbanBoardId() == null || taskDetailsRq.getKanbanBoardId().equals(0L)) {
            errors.add("Рабочее пространство задачи должно быть указано.");
        }
        if (taskDetailsRq.getBeginDate() == null) {
            errors.add("Дата начала задачи должна быть указана.");
        }
        if (taskDetailsRq.getEndDate() == null) {
            errors.add("Дата завершения задачи должна быть указана.");
        }
        if (taskDetailsRq.getBeginDate().compareTo(taskDetailsRq.getEndDate()) >= 0) {
            errors.add("Дата начала задачи должна быть раньше даты окончания.");
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.compareTo(taskDetailsRq.getEndDate()) >= 0) {
            errors.add("Дата завершения задачи должна быть позже текущего времени.");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public void validateUser(Long id) {
        List<String> errors = new ArrayList<>();
        Task task = taskService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять исполнителя задачи"));
        if (task.getState() != State.CREATED) {
            errors.add("Ошибка! Исполнитель может быть изменён только при создании задачи");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}