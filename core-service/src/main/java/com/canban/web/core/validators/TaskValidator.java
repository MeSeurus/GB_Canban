package com.canban.web.core.validators;

import com.canban.api.core.TaskDto;
import com.canban.api.exceptions.ValidationException;
import com.canban.web.core.dto.TaskDetailsRq;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskValidator {

    public void validate(TaskDetailsRq taskDetailsRq) {
        List<String> errors = new ArrayList<>();
        if (taskDetailsRq.getTitle() != null && taskDetailsRq.getTitle().isBlank()) {
            errors.add("Название задачи должно быть указано.");
        }
        if (taskDetailsRq.getKanbanName() == null || taskDetailsRq.getKanbanName().isBlank()) {
            errors.add("Рабочее пространство задачи должно быть указано.");
        }
        if (taskDetailsRq.getBeginDate() == null) {
            errors.add("Дата начала события должна быть указана.");
        }
        if (taskDetailsRq.getEndDate() == null) {
            errors.add("Дата завершения события должна быть указана.");
        }
        if (taskDetailsRq.getBeginDate().compareTo(taskDetailsRq.getEndDate()) >= 0 ){
            errors.add("Дата начала события должна быть раньше даты окончания.");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
