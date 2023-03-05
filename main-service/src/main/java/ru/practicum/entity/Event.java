package ru.practicum.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Builder.Default
    private Long confirmedRequests = 0L;
//    private Long confirmedRequests;
    @Column(nullable = false)
    private Timestamp createdOn;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Timestamp eventDate;
    @ManyToOne
    @JoinColumn(name = "initiator_id", nullable = false)
    private User initiator;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    @Column(nullable = false)
    private boolean paid;
    @Column(name = "participant_limit", nullable = false)
    private int participantLimit;
    @Column(name = "published_on")
    private Timestamp publishedOn;
    @Column(name = "request_moderation", nullable = false)
    @Builder.Default
    private boolean requestModeration = true;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state = State.PENDING;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    @Builder.Default
    private Long views = 0L;
}
