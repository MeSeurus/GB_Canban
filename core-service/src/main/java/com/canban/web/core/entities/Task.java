package com.canban.web.core.entities;
import com.canban.web.core.enums.Priority;
import com.canban.web.core.enums.State;
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
    @Column(
            name = "actual_end_date"
    )
    private LocalDateTime actualEndDate;

    @Column(
            name = "state"
    )
    @Enumerated(EnumType.STRING)
    @DefaultValue("CREATED")
    @NotNull
    private State state;


    @Column(
            name = "priority"
    )
    @Enumerated(EnumType.STRING)
    @NotNull
    @DefaultValue("NORMAL")
    private Priority priority;

    @Column(
            name = "kanban_name"
    )
    @NotNull
    private String kanbanName;

    public Task(Long id,
                String title,
                String content,
                String username,
                LocalDateTime beginDate,
                LocalDateTime dueDate,
                LocalDateTime actualEndDate,
                State state,
                Priority priority,
                String kanbanName ) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.beginDate = beginDate;

        this.endDate = dueDate;
        this.actualEndDate = actualEndDate;

        this.state = state;
        this.priority = priority;
        this.kanbanName = kanbanName;
    }

    public static TaskBuilder taskBuilder() {
        return new TaskBuilder();
    }


    public static class TaskBuilder {
        private Long id;
        private String title;
        private String content;
        private String username;
        private LocalDateTime beginDate;

        private LocalDateTime endDate;
        private LocalDateTime actualEndDate;
        private State state;
        private Priority priority;
        private String kanbanName;



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
        public TaskBuilder username(final String username) {
            this.username = username;
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

        public Task.TaskBuilder kanbanName(final String kanbanName) {
            this.kanbanName = kanbanName;
            return this;
        }

        public Task build() {

            return new Task(
                    this.id,
                    this.title,
                    this.content,
                    this.username,
                    this.beginDate,
                    this.endDate,
                    this.actualEndDate,
                    this.state,
                    this.priority,
                    this.kanbanName);

        }

    }
}