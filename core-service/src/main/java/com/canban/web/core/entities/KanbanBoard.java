package com.canban.web.core.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "kanban_boards")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class KanbanBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creator")
    private String creator;

    /*public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String creator

        private Builder() {
        }

        public Builder id(final Long id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder creator(final String creator) {
            this.creator = creator;
            return this;
        }

        public KanbanBoard build() {
            return new KanbanBoard(this.id, this.name, this.creator);
        }

    }*/

}
