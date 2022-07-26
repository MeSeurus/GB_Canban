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
        if (taskDetailsRq.getDueDate() != null) {
            errors.add("Дата завершения события должна быть указана.");
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
        if (taskDto.getContent() != null && taskDto.getContent().isBlank()) {
            errors.add("Описание задачи должно быть указано.");
        }
        if (taskDto.getUsername() != null && taskDto.getUsername().isBlank()) {
            errors.add("Имя пользователя должно быть указано.");
        }
        if (taskDto.getState() != null) {
            errors.add("Статус задачи должен быть указан.");
        }
        if (taskDto.getPriority() != null) {
            errors.add("Приоритет задачи должен быть указан.");
        }
        if (taskDto.getKanbanName() != null && taskDto.getKanbanName().isBlank()) {
            errors.add("Рабочее пространство задачи должно быть указано.");
        }
        if (taskDto.getEventDate() != null) {
            errors.add("Дата начала события должна быть указана.");
        }
        if (taskDto.getDueDate() != null) {
            errors.add("Дата завершения события должна быть указана.");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
