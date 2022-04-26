package com.example.micrigramm.entity;

import lombok.*;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "publication")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
    private String description;
    private String picture;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publication)) return false;
        Publication that = (Publication) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public static void setDateAdded(Publication publication, Double aDouble) {

    }

    public Publication(String picture, User author, LocalDateTime dateAdded) {
        this.picture = picture;
        this.author = author;
        this.dateAdded = dateAdded;
    }
}
