package com.canban.web.core.entities;

import com.canban.api.core.Priority;
import com.canban.api.core.State;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "tasks")

@Getter
@Setter
@ToString
public class Task extends Event{

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "state")
    private State state;

    @Column(name = "priority")
    private Priority priority;

    @Column(name = "kanban_Name")
    private String kanbanName;


    public Task(Long id, String title, String content, String userName, LocalDateTime eventDate, LocalDateTime dueDate, State state, Priority priority, String kanbanName) {
    }

    public Task() {

    }

    public static TaskBuilder taskBuilder() {
        return new TaskBuilder();
    }


    public static class TaskBuilder {
        private Long id;
        private String title;
        private String content;
        private String userName;
        private LocalDateTime eventDate;
        private State state;
        private Priority priority;
        private String kanbanName;
        private LocalDateTime dueDate;


        TaskBuilder() {
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

        public TaskBuilder userName(final String userName) {
            this.userName = userName;
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
            return new Task(this.id, this.title, this.content, this.userName, this.eventDate, this.dueDate, this.state, this.priority, this.kanbanName);
        }

    }
}
