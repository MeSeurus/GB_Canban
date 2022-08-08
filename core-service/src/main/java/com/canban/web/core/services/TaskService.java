package com.canban.web.core.services;


import com.canban.api.core.Priority;
import com.canban.api.core.State;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.core.dto.TaskDetailsRq;
import com.canban.web.core.entities.Task;
import com.canban.web.core.mapper.DateFormatter;
import com.canban.web.core.repositories.TaskRepository;
import com.canban.web.core.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final DateFormatter dateFormatter;
    private final TaskRepository taskRepository;

    private List<Task> tasks = new CopyOnWriteArrayList<>();

    public List<Task> findAll(Long boardId,
                        String titlePart,
                        String userCreator,
                        String userExecutor,
                        String maxBeginDate,
                        String minBeginDate,
                        String maxEndDate,
                        String minEndDate,
                        String maxActualEndDate,
                        String minActualEndDate,
                        String stateStr,
                        String priorityStr) {
        Specification<Task> spec = Specification.where(null);
        spec = spec.and(TaskSpecification.boardIdEqual(boardId)); // обязательное поле
        if (titlePart != null) {
            spec = spec.and(TaskSpecification.titleLike(titlePart));
        }
        if (userCreator != null) {
            spec = spec.and(TaskSpecification.usernameCreatorEqual(userCreator));
        }
        if (userExecutor != null) {
            spec = spec.and(TaskSpecification.usernameExecutorEqual(userExecutor));
        }
        if (maxBeginDate != null) {
            spec = spec.and(TaskSpecification.beginDateLessOrEqualsThen(dateFormatter.stringToDate(maxBeginDate)));
        }
        if (minBeginDate != null) {
            spec = spec.and(TaskSpecification.beginDateGreaterOrEqualsThen(dateFormatter.stringToDate(minBeginDate)));
        }
        if (maxEndDate != null) {
            spec = spec.and(TaskSpecification.endDateLessOrEqualsThen(dateFormatter.stringToDate(maxEndDate)));
        }
        if (minEndDate != null) {
            spec = spec.and(TaskSpecification.endDateGreaterOrEqualsThen(dateFormatter.stringToDate(minEndDate)));
        }
        if (maxActualEndDate != null) {
            spec = spec.and(TaskSpecification.actualEndDateLessOrEqualsThen(dateFormatter.stringToDate(maxEndDate)));
        }
        if (minActualEndDate != null) {
            spec = spec.and(TaskSpecification.actualEndDateGreaterOrEqualsThen(dateFormatter.stringToDate(minEndDate)));
        }
        if (stateStr != null) {
            spec = spec.and(TaskSpecification.stateEquals(State.valueOf(stateStr)));
        }
        if (priorityStr != null) {
            spec = spec.and(TaskSpecification.priorityEquals(Priority.valueOf(priorityStr)));
        }
        return taskRepository.findAll(spec);
    }

    public void createTask(String username, TaskDetailsRq taskDetailsRq) {
        Task task = null;
        if (taskDetailsRq.getUserExecutor() != null || !taskDetailsRq.getUserExecutor().isEmpty()) {
            task = Task.taskBuilder()
                    .title(taskDetailsRq.getTitle())
                    .content(taskDetailsRq.getContent())
                    .userCreator(username)
                    .userExecutor(taskDetailsRq.getUserExecutor())
                    .beginDate(taskDetailsRq.getBeginDate())
                    .endDate(taskDetailsRq.getEndDate())
                    .actualEndDate(taskDetailsRq.getActualEndDate())
                    .kanbanBoardId(taskDetailsRq.getKanbanBoardId())
                    .state(taskDetailsRq.getState())
                    .priority(taskDetailsRq.getPriority())
                    .build();
        } else {
            task = Task.taskBuilder()
                    .title(taskDetailsRq.getTitle())
                    .content(taskDetailsRq.getContent())
                    .userCreator(username)
                    .beginDate(taskDetailsRq.getBeginDate())
                    .endDate(taskDetailsRq.getEndDate())
                    .actualEndDate(taskDetailsRq.getActualEndDate())
                    .kanbanBoardId(taskDetailsRq.getKanbanBoardId())
                    .state(taskDetailsRq.getState())
                    .priority(taskDetailsRq.getPriority())
                    .build();
        }
        taskRepository.save(task);
        tasks.add(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public void changeTitle(Long id, String title) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change task's title. Task not found"));
        task.setTitle(title);
    }

    @Transactional
    public void changeContent(Long id, String content) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change task's content. Task not found"));
        task.setContent(content);
    }

    @Transactional
    public void changeExecutorUsername(Long id, String username) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change task's username. Task not found"));
        task.setUserExecutor(username);
    }

    @Transactional
    public void changeBeginDate(Long id, LocalDateTime beginDate) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change task's begin date. Task not found"));
        task.setBeginDate(beginDate);
    }

    @Transactional
    public void changeEndDate(Long id, LocalDateTime endDate) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change task's end date. Task not found"));
        task.setBeginDate(endDate);
    }

    @Transactional
    public void changeState(Long id, State state) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change task's state. Task not found"));
        task.setState(state);
    }

    @Transactional
    public void changePriority(Long id, Priority priority) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change task's priority. Task not found"));
        task.setPriority(priority);
    }

    @Transactional
    public void changeActualEndDate(Long id, LocalDateTime actualEndDate) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change task's actual end date. Task not found"));
        task.setActualEndDate(actualEndDate);
    }
    public List<Task> findAllForAnalytics() {
        return tasks;
    }

    public void clearList() {
        tasks.clear();
    }

}