package com.canban.web.core.contollers;

import com.canban.web.core.mapper.EventMapper;
import com.canban.web.core.services.EventService;
import com.canban.web.core.services.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Задачи", description = "Методы работы с задачами")
public class TaskController {

    private final TaskService taskService;
    private final EventMapper eventMapper;


}
