package com.canban.web.core.validators;

import com.canban.api.exceptions.ValidationException;
import com.canban.web.core.dto.KanbanBoardDetailsRq;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KanbanValidator {

    public void validate(KanbanBoardDetailsRq kanbanBoardDetailsRq) {
        List<String> errors = new ArrayList<>();

        if (kanbanBoardDetailsRq.getTitle() == null) {
            errors.add("Название канбан-доски должно быть указано.");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
