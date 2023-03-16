package ru.practicum.entity;

import lombok.*;
import ru.practicum.validation.AfterNow;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "events")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String annotation;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @Column(nullable = false)
    private Integer confirmedRequests;
    @Column(nullable = false)
    private Timestamp createdOn;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @AfterNow
    private Timestamp eventDate;
    @ManyToOne
    @JoinColumn(name = "initiator_id", nullable = false)
    private User initiator;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    @Column(nullable = false)
    private Boolean paid;
    @Column(name = "participant_limit", nullable = false)
    private Integer participantLimit;
    @Column(name = "published_on")
    private Timestamp publishedOn;
    @Column(name = "request_moderation", nullable = false)
    private Boolean requestModeration;
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer views;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(annotation, event.annotation)
                && Objects.equals(category, event.category)
                && Objects.equals(createdOn, event.createdOn)
                && Objects.equals(description, event.description)
                && Objects.equals(eventDate, event.eventDate)
                && Objects.equals(initiator, event.initiator)
                && Objects.equals(location, event.location)
                && Objects.equals(paid, event.paid)
                && Objects.equals(publishedOn, event.publishedOn)
                && Objects.equals(title, event.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annotation, category, createdOn, description,
                eventDate, initiator, location, paid, publishedOn, title);
    }
}
