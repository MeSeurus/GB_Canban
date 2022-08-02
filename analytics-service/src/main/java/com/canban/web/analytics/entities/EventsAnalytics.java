package com.canban.web.analytics.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "events_analytics")
public class EventsAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "event_id")
    @NotNull
    private Long eventId;

    @Column(name = "event_title")
    @NotNull
    private String eventTitle;

    @Column(name = "event_username")
    private String eventUsername;

    @Column(name = "event_begin_date")
    @NotNull
    private LocalDateTime eventBeginDate;

    @Column(name = "event_end_date")
    @NotNull
    private LocalDateTime eventEndDate;

    @ElementCollection
    @CollectionTable(
            name = "events_users",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id")
    )
    @Column(name = "added_usernames")
    private Set<String> users;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
