package com.example.micrigramm.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "subscribe")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private User subscription;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscribe)) return false;
        Subscribe that = (Subscribe) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Subscribe(User subscriber, User subscription, LocalDateTime dateAdded) {
        this.subscriber = subscriber;
        this.subscription = subscription;
        this.dateAdded = dateAdded;
    }
}
