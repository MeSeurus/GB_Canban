package com.canban.web.core.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "kanban_boards")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class KanbanBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creator")
    private String creator;

    @ElementCollection
    @CollectionTable(
            name = "kanban_boards_users",
            joinColumns = @JoinColumn(name = "kanban_board_id", referencedColumnName = "id")
    )
    @Column(name = "username")
    private Set<String> users;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
