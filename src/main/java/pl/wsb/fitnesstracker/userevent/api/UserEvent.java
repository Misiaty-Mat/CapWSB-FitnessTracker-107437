package pl.wsb.fitnesstracker.userevent.api;

import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.event.Event;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "user_event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String status;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Event event;
}
