package com.canban.web.core.validators;

import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.api.exceptions.ValidationException;
import com.canban.web.core.dto.TaskDetailsRq;
import com.canban.web.core.entities.Task;
import com.canban.web.core.enums.State;
import com.canban.web.core.repositories.TaskRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskValidator {

    TaskRepository taskRepository;

    public void validate(TaskDetailsRq taskDetailsRq) {
        List<String> errors = new ArrayList<>();
        if (taskDetailsRq.getTitle() != null && taskDetailsRq.getTitle().isBlank()) {
            errors.add("Название задачи должно быть указано.");
        }
        if (taskDetailsRq.getKanbanName() == null || taskDetailsRq.getKanbanName().isBlank()) {
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

        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change task's username."));
        if (task.getState() != State.CREATED) {
            errors.add("Ошибка. Исполнитель может быть изменен только в статусе СОЗДАНО ");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}