package com.canban.web.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "events")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Event extends AbstractEvent {
    @Column(name = "username")
    private String username;
    public static EventBuilder builder() {
        return new Event.EventBuilder();
    }

    public Event(Long id,
                 String title,
                 String content,
                 String username,
                 LocalDateTime beginDate,
                 LocalDateTime endDate
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.beginDate = beginDate;
        this.endDate = endDate;

    }

    public static class EventBuilder {
        private Long id;
        private String title;
        private String content;
        private String username;
        private LocalDateTime beginDate;
        private LocalDateTime endDate;

        EventBuilder() {
        }

        public Event.EventBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public Event.EventBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public Event.EventBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public Event.EventBuilder username(final String username) {
            this.username = username;
            return this;
        }

        public Event.EventBuilder beginDate(final LocalDateTime beginDate) {
            this.beginDate = beginDate;
            return this;
        }

        public Event.EventBuilder endDate(final LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Event build() {
            return new Event(this.id, this.title, this.content, this.username, this.beginDate, this.endDate);
        }
    }
}

