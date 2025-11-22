package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.userevent.api.UserEvent;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Event {

    @Id
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private String country;

    @Column
    private String city;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<UserEvent> userEvents;
}
