package com.canban.web.core.validators;

import com.canban.api.exceptions.ValidationException;
import com.canban.web.core.dto.EventDetailsRq;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventValidator {

    public void validate(EventDetailsRq eventDetailsRq) {
        List<String> errors = new ArrayList<>();

        if (eventDetailsRq.getTitle() == null || eventDetailsRq.getTitle().isBlank()) {
            errors.add("Название события должно быть указано.");
        }

        if (eventDetailsRq.getBeginDate() == null) {
            errors.add("Дата проведения события должна быть указана.");
        }

        if (eventDetailsRq.getEndDate() == null) {
            errors.add("Дата окончания события должна быть указана.");
        }

        if (eventDetailsRq.getBeginDate().compareTo(eventDetailsRq.getEndDate()) >= 0) {
            errors.add("Дата начала события должна быть раньше даты окончания.");
        }

        LocalDateTime now = LocalDateTime.now();
        if (now.compareTo(eventDetailsRq.getEndDate()) >= 0) {
            errors.add("Дата завершения задачи должна быть позже текущего времени.");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}