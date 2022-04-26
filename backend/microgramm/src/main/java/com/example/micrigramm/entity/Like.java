package com.example.micrigramm.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "like_table")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "like_owner_id")
    private User likeOwner;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Like)) return false;
        Like that = (Like) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Like(User likeOwner, Publication publication, LocalDateTime dateAdded) {
        this.likeOwner = likeOwner;
        this.publication = publication;
        this.dateAdded = dateAdded;
    }
}
