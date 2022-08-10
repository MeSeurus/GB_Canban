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
            errors.add("Title  must be filled in.");
        }

        if (eventDetailsRq.getBeginDate() == null) {
            errors.add("Begin date  must be filled in.");
        }

        if (eventDetailsRq.getEndDate() == null) {
            errors.add("End date must be filled in.");
        }

        if (eventDetailsRq.getBeginDate().compareTo(eventDetailsRq.getEndDate()) >= 0) {
            errors.add("The begin date of the event must be earlier than the end date.");
        }

        LocalDateTime now = LocalDateTime.now();
        if (now.compareTo(eventDetailsRq.getEndDate()) >= 0) {
            errors.add("The event completion date must be later than the current time.");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}