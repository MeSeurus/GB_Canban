package com.canban.web.core.entities;
import com.canban.api.core.Priority;
import com.canban.api.core.State;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Task extends AbstractEvent{

    @Column(name = "user_creator")
    private String userCreator;

    @Column(name = "user_executor")
    private String userExecutor;

    @Column(name = "actual_end_date")
    private LocalDateTime actualEndDate;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @DefaultValue("CREATED")
    @NotNull
    private State state;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    @NotNull
    @DefaultValue("NORMAL")
    private Priority priority;

    @Column(name = "kanban_board_id")
    @NotNull
    private Long kanbanBoardId;

    public Task(Long id,
                String title,
                String content,
                String userCreator,
                String userExecutor,
                LocalDateTime beginDate,
                LocalDateTime endDate,
                LocalDateTime actualEndDate,
                State state,
                Priority priority,
                Long kanbanBoardId ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userCreator = userCreator;
        this.userExecutor = userExecutor;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.actualEndDate = actualEndDate;
        this.state = state;
        this.priority = priority;
        this.kanbanBoardId = kanbanBoardId;
    }

    public static TaskBuilder taskBuilder() {
        return new TaskBuilder();
    }

    public static class TaskBuilder {
        private Long id;
        private String title;
        private String content;
        private String userCreator;
        private String userExecutor;
        private LocalDateTime beginDate;
        private LocalDateTime endDate;
        private LocalDateTime actualEndDate;
        private State state;
        private Priority priority;
        private Long kanbanBoardId;

        private TaskBuilder() {
        }

        public TaskBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public TaskBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public TaskBuilder userCreator(final String userCreator) {
            this.userCreator = userCreator;
            return this;
        }

        public TaskBuilder userExecutor(final String userExecutor) {
            this.userExecutor = userExecutor;
            return this;
        }

        public TaskBuilder beginDate(final LocalDateTime eventDate) {
            this.beginDate = eventDate;
            return this;
        }

        public Task.TaskBuilder endDate(final LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Task.TaskBuilder actualEndDate(final LocalDateTime actualEndDate) {
            this.actualEndDate = actualEndDate;
            return this;
        }

        public TaskBuilder state(final State state) {
            this.state = state;
            return this;
        }

        public Task.TaskBuilder priority(final Priority priority) {
            this.priority = priority;
            return this;
        }

        public Task.TaskBuilder kanbanBoardId(final Long kanbanBoardId) {
            this.kanbanBoardId = kanbanBoardId;
            return this;
        }

        public Task build() {
            return new Task(
                    this.id,
                    this.title,
                    this.content,
                    this.userCreator,
                    this.userExecutor,
                    this.beginDate,
                    this.endDate,
                    this.actualEndDate,
                    this.state,
                    this.priority,
                    this.kanbanBoardId);
        }

    }
}