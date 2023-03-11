package ru.practicum.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.validation.AfterNow;

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
    private Integer confirmedRequests;
    @Column(nullable = false)
    private Timestamp createdOn;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @AfterNow
    //@PlusTwoHours
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

}
