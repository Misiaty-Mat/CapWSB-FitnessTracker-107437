package pl.wsb.fitnesstracker.training.api;

import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.statistics.api.Statistics;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.workoutsession.WorkoutSession;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    @Enumerated
    private ActivityType activityType;

    @Column
    private double distance;

    @Column
    private double averageSpeed;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private List<WorkoutSession> workoutSessions;

    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}