package com.canban.web.analytics.entities;

import com.canban.api.core.Priority;
import com.canban.api.core.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.ws.rs.DefaultValue;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks_analytics")
public class TasksAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "task_username")
    private String taskUsername;

    @Column(name = "task_begin_date")
    private LocalDateTime taskBeginDate;

    @Column(name = "task_end_date")
    private LocalDateTime taskEndDate;

    @Column(name = "task_actual_end_date")
    private LocalDateTime taskActualEndDate;

    @Column(name = "task_state")
    @Enumerated(EnumType.STRING)
    @DefaultValue("CREATED")
    private State taskState;

    @Column(name = "task_priority")
    @Enumerated(EnumType.STRING)
    @DefaultValue("NORMAL")
    private Priority taskPriority;

    @Column(name = "task_kanban_name")
    private String taskKanbanName;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    public TasksAnalytics(Long taskId, String taskTitle, String taskUsername, LocalDateTime taskBeginDate, LocalDateTime taskEndDate, LocalDateTime taskActualEndDate, State taskState, Priority taskPriority, String taskKanbanName) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskUsername = taskUsername;
        this.taskBeginDate = taskBeginDate;
        this.taskEndDate = taskEndDate;
        this.taskActualEndDate = taskActualEndDate;
        this.taskState = taskState;
        this.taskPriority = taskPriority;
        this.taskKanbanName = taskKanbanName;
    }
}
