package com.canban.web.core.services;


import com.canban.api.core.Priority;
import com.canban.api.core.State;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.core.dto.TaskDetailsForSearchRq;
import com.canban.web.core.dto.TaskDetailsRq;
import com.canban.web.core.entities.Task;
import com.canban.web.core.mapper.DateFormatter;
import com.canban.web.core.mapper.TaskAnalyticsMapper;
import com.canban.web.core.repositories.TaskRepository;
import com.canban.web.core.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final DateFormatter dateFormatter;
    private final TaskRepository taskRepository;

    private final TaskForAnalyticsService taskForAnalyticsService;

    private final TaskAnalyticsMapper taskAnalyticsMapper;

    public List<Task> searchAllTasksByBoardId(TaskDetailsForSearchRq taskDetailsForSearchRq) {
        Specification<Task> spec = Specification.where(null);
        spec = spec.and(TaskSpecification.boardIdEqual(taskDetailsForSearchRq.getBoardId())); // обязательное поле
        if (taskDetailsForSearchRq.getTitlePart() != null) {
            spec = spec.and(TaskSpecification.titleLike(taskDetailsForSearchRq.getTitlePart()));
        }
        if (taskDetailsForSearchRq.getUserCreator() != null) {
            spec = spec.and(TaskSpecification.usernameCreatorEqual(taskDetailsForSearchRq.getUserCreator()));
        }
        if (taskDetailsForSearchRq.getUserExecutor() != null) {
            spec = spec.and(TaskSpecification.usernameExecutorEqual(taskDetailsForSearchRq.getUserExecutor()));
        }
        if (taskDetailsForSearchRq.getMaxBeginDate() != null) {
            spec = spec.and(TaskSpecification.beginDateLessOrEqualsThen(dateFormatter.stringToDate(taskDetailsForSearchRq.getMaxBeginDate())));
        }
        if (taskDetailsForSearchRq.getMinBeginDate() != null) {
            spec = spec.and(TaskSpecification.beginDateGreaterOrEqualsThen(dateFormatter.stringToDate(taskDetailsForSearchRq.getMinBeginDate())));
        }
        if (taskDetailsForSearchRq.getMaxEndDate() != null) {
            spec = spec.and(TaskSpecification.endDateLessOrEqualsThen(dateFormatter.stringToDate(taskDetailsForSearchRq.getMaxEndDate())));
        }
        if (taskDetailsForSearchRq.getMinEndDate() != null) {
            spec = spec.and(TaskSpecification.endDateGreaterOrEqualsThen(dateFormatter.stringToDate(taskDetailsForSearchRq.getMinEndDate())));
        }
        if (taskDetailsForSearchRq.getMaxActualEndDate() != null) {
            spec = spec.and(TaskSpecification.actualEndDateLessOrEqualsThen(dateFormatter.stringToDate(taskDetailsForSearchRq.getMaxActualEndDate())));
        }
        if (taskDetailsForSearchRq.getMinActualEndDate() != null) {
            spec = spec.and(TaskSpecification.actualEndDateGreaterOrEqualsThen(dateFormatter.stringToDate(taskDetailsForSearchRq.getMinActualEndDate())));
        }
        if (taskDetailsForSearchRq.getState() != null) {
            spec = spec.and(TaskSpecification.stateEquals(taskDetailsForSearchRq.getState()));
        }
        if (taskDetailsForSearchRq.getPriority() != null) {
            spec = spec.and(TaskSpecification.priorityEquals(taskDetailsForSearchRq.getPriority()));
        }
        return taskRepository.findAll(spec);
    }

    @Transactional
    public void createTask(String username, TaskDetailsRq taskDetailsRq) {
        Task task = Task.taskBuilder()
                .title(taskDetailsRq.getTitle())
                .content(taskDetailsRq.getContent())
                .userCreator(username)
                .userExecutor(taskDetailsRq.getUserExecutor())
                .actualEndDate(taskDetailsRq.getActualEndDate())
                .beginDate(taskDetailsRq.getBeginDate())
                .endDate(taskDetailsRq.getEndDate())
                .kanbanBoardId(taskDetailsRq.getKanbanBoardId())
                .state(taskDetailsRq.getState())
                .priority(taskDetailsRq.getPriority())
                .build();
        taskRepository.save(task);
        taskForAnalyticsService.createTaskForAnalytics(taskAnalyticsMapper.taskToTaskForAnalytics(task));
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public void changeTitle(Long id, String title) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять название задачи. Задача не найдена"));
        task.setTitle(title);
        taskForAnalyticsService.createTaskForAnalytics(taskAnalyticsMapper.taskToTaskForAnalytics(task));
    }

    @Transactional
    public void changeContent(Long id, String content) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять содержимое задачи. Задача не найдена"));
        task.setContent(content);
        taskForAnalyticsService.createTaskForAnalytics(taskAnalyticsMapper.taskToTaskForAnalytics(task));
    }

    @Transactional
    public void changeExecutorUsername(Long id, String username) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять исполнителя задачи. Задача не найдена"));
        task.setUserExecutor(username);
        taskForAnalyticsService.createTaskForAnalytics(taskAnalyticsMapper.taskToTaskForAnalytics(task));
    }

    @Transactional
    public void changeBeginDate(Long id, LocalDateTime beginDate) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять дату начала задачи. Задача не найдена"));
        task.setBeginDate(beginDate);
        taskForAnalyticsService.createTaskForAnalytics(taskAnalyticsMapper.taskToTaskForAnalytics(task));
    }

    @Transactional
    public void changeEndDate(Long id, LocalDateTime endDate) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять дату окончания задачи. Задача не найдена"));
        task.setBeginDate(endDate);
        taskForAnalyticsService.createTaskForAnalytics(taskAnalyticsMapper.taskToTaskForAnalytics(task));
    }

    @Transactional
    public void changeState(Long id, State state) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять состояние задачи. Задача не найдена"));
        task.setState(state);
        taskForAnalyticsService.createTaskForAnalytics(taskAnalyticsMapper.taskToTaskForAnalytics(task));
    }

    @Transactional
    public void changePriority(Long id, Priority priority) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять приоритет задачи. Задача не найдена"));
        task.setPriority(priority);
        taskForAnalyticsService.createTaskForAnalytics(taskAnalyticsMapper.taskToTaskForAnalytics(task));
    }

    @Transactional
    public void changeActualEndDate(Long id, LocalDateTime actualEndDate) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять дату окончания задачи. Задача не найдена"));
        task.setActualEndDate(actualEndDate);
        taskForAnalyticsService.createTaskForAnalytics(taskAnalyticsMapper.taskToTaskForAnalytics(task));
    }

}