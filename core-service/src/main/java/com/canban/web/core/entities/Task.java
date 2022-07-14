package com.canban.web.core.entities;

import com.canban.web.core.enums.Priority;
import com.canban.web.core.enums.State;
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

    @Column(name = "canbanName")
    private String canbanName;

    public Task(Long id, String title, String content, String userName, LocalDateTime eventDate, LocalDateTime dueDate, State state, Priority priority, String canbanName) {
    }



    public static class TaskBuilder {
        private Long id;
        private String title;
        private String content;
        private String userName;
        private LocalDateTime eventDate;
        private State state;
        private Priority priority;
        private String canbanName;
        private LocalDateTime dueDate;



        TaskBuilder() {
        }

        public Task.TaskBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public Task.TaskBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public Task.TaskBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public Task.TaskBuilder userName(final String userName) {
            this.userName = userName;
            return this;
        }

        public Task.TaskBuilder eventDate(final LocalDateTime eventDate) {
            this.eventDate = eventDate;
            return this;
        }
        public Task.TaskBuilder state(final State state) {
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

        public Task.TaskBuilder canbanName(final String canbanName) {
            this.canbanName = canbanName;
            return this;
        }

        public Task build() {
            return new Task(this.id, this.title, this.content, this.userName, this.eventDate, this.dueDate, this.state, this.priority, this.canbanName);
        }

    }
}
