package com.canban.web.core.entities;

import com.canban.api.core.Priority;
import com.canban.api.core.State;
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
    private LocalDateTime due_date;

    @Column(name = "state")
//    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "priority")
//    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "kanban_name")
    private String kanban_name;


    public Task(Long id, String title, String content, String username, LocalDateTime event_date, LocalDateTime due_date, State state, Priority priority, String kanban_name) {
    }


    public static TaskBuilder taskBuilder() {
        return new TaskBuilder();
    }


    public static class TaskBuilder {
        private Long id;
        private String title;
        private String content;
        private String username;
        private LocalDateTime event_date;
        private State state;
        private Priority priority;
        private String kanban_name;
        private LocalDateTime due_date;


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

        public TaskBuilder userName(final String username) {
            this.username = username;
            return this;
        }

        public TaskBuilder eventDate(final LocalDateTime event_date) {
            this.event_date = event_date;
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

        public Task.TaskBuilder due_date(final LocalDateTime due_date) {
            this.due_date = due_date;
            return this;
        }

        public Task.TaskBuilder kanbanName(final String kanbanName) {
            this.kanban_name = kanban_name;
            return this;
        }

        public Task build() {
            return new Task(this.id, this.title, this.content, this.username, this.event_date, this.due_date, this.state, this.priority, this.kanban_name);
        }

    }
}
