package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDate;

@Entity
@Table(name = "workout_session")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Training training;

    @Column
    private LocalDate timestamp;

    @Column
    private double startLatitude;

    @Column
    private double startLongitude;

    @Column
    private double endLatitude;

    @Column
    private double endLongitude;

    @Column
    private double altitude;
}
