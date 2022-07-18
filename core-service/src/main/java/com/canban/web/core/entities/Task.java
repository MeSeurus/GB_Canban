package com.canban.web.core.entities;

import com.canban.web.core.enums.Priority;
import com.canban.web.core.enums.State;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Task extends Event{

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(name = "priority")
    @Enumerated(value = EnumType.STRING)
    private Priority priority;

    @Column(name = "kanban_name")
    private String kanbanName;

    public Task(Long id, String title, String content, String username, LocalDateTime eventDate, LocalDateTime dueDate, State state, Priority priority, String kanbanName) {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
        this.setUsername(username);
        this.setEventDate(eventDate);
        this.setDueDate(dueDate);
        this.setState(state);
        this.setPriority(priority);
        this.setKanbanName(kanbanName);
    }


    public static TaskBuilder taskBuilder() {
        return new TaskBuilder();
    }

    public static class TaskBuilder {
        private Long id;
        private String title;
        private String content;
        private String username;
        private LocalDateTime eventDate;
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

        public TaskBuilder eventDate(final LocalDateTime eventDate) {
            this.eventDate = eventDate;
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
            return new Task(this.id, this.title, this.content, this.username, this.eventDate, this.dueDate, this.state, this.priority, this.kanbanName);
        }

    }
}
