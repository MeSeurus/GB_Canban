package com.canban.web.core.validators;

import com.canban.api.core.TaskDto;
import com.canban.api.exceptions.ValidationException;
import com.canban.web.core.dto.TaskDetailsRq;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskValidator {

    /**
     * Заметки для Алексея.
     * Нужно удалить лишние проверки и подкорректировать бд в соотвествии с тем, что у нас обязательно, а что нет.
     * Написала валидацию на все входящие поля, лишнее УДАЛИТЬ. (например, мне кажется, контент можно и пустым оставлять)
     */
    public void validate(TaskDetailsRq taskDetailsRq) {
        List<String> errors = new ArrayList<>();
        if (taskDetailsRq.getTitle() != null && taskDetailsRq.getTitle().isBlank()) {
            errors.add("Название задачи должно быть указано.");
        }
        if (taskDetailsRq.getContent() != null && taskDetailsRq.getContent().isBlank()) {
            errors.add("Описание задачи должно быть указано.");
        }

        if (taskDetailsRq.getState() != null) {
            errors.add("Статус задачи должен быть указан.");
        }
        if (taskDetailsRq.getPriority() != null) {
            errors.add("Приоритет задачи должен быть указан.");
        }
        if (taskDetailsRq.getKanbanName() != null && taskDetailsRq.getKanbanName().isBlank()) {
            errors.add("Рабочее пространство задачи должно быть указан.");
        }
        if (taskDetailsRq.getBeginDate() != null) {
            errors.add("Дата начала события должна быть указана.");
        }
        if (taskDetailsRq.getEndDate() != null) {
            errors.add("Дата завершения события должна быть указана.");
        }
        if (taskDetailsRq.getBeginDate().compareTo(taskDetailsRq.getEndDate()) >= 0 ){
            errors.add("Дата начала задачи должна быть раньше даты окончания.");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    /**
     * Заметки для Алексея
     * Второй валидатор, т.к. в методе addTask на вход приходит TaskDto, хотя я предполагала,
     * что нам на вход нужна другая дто (TaskDetailsRq именно как входящая дто из которой мы соберем сущность).
     * Удалить лишний метод, решив что нам приходит на вход
     */
    public void validate(TaskDto taskDto) {
        List<String> errors = new ArrayList<>();
        if (taskDto.getTitle() != null && taskDto.getTitle().isBlank()) {
            errors.add("Название задачи должно быть указано.");
        }


        if (taskDto.getKanbanName() != null && taskDto.getKanbanName().isBlank()) {
            errors.add("Рабочее пространство задачи должно быть указано.");
        }

        if (taskDto.getBeginDate() != null) {
            errors.add("Дата начала события должна быть указана.");
        }

        if (taskDto.getEndDate() != null) {
            errors.add("Дата завершения события должна быть указана.");
        }
        if (taskDto.getBeginDate().compareTo(taskDto.getEndDate()) >= 0 ){
            errors.add("Дата начала события должна быть раньше даты окончания.");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
