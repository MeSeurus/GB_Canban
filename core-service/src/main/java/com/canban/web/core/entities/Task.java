package com.canban.web.core.entities;
import com.canban.web.core.enums.Priority;
import com.canban.web.core.enums.State;
import lombok.*;
import javax.persistence.*;
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
            name = "due_date"
    )
    private LocalDateTime dueDate;
    @Column(
            name = "state"
    )
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(
            name = "priority"
    )
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(
            name = "kanban_name"
    )
    private String kanbanName;

    public Task(Long id, String title, String content, String username, LocalDateTime beginDate, LocalDateTime dueDate, State state, Priority priority, String kanbanName ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.beginDate = beginDate;
        this.dueDate = dueDate;
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
        private State state;
        private Priority priority;
        private String kanbanName;
        private LocalDateTime dueDate;
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
        public TaskBuilder state(final State state) {
            this.state = state;
            return this;
        }
        public Task.TaskBuilder priority(final Priority priority) {
            this.priority = priority;
            return this;
        }
        public Task.TaskBuilder dueDate(final LocalDateTime dueDate) {
            this.dueDate = dueDate;
            return this;
        }
        public Task.TaskBuilder kanbanName(final String kanbanName) {
            this.kanbanName = kanbanName;
            return this;
        }

        public Task build() {
            return new Task(this.id, this.title, this.content, this.username, this.beginDate, this.dueDate, this.state, this.priority, this.kanbanName);
        }

    }
}