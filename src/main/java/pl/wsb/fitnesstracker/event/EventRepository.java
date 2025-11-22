package pl.wsb.fitnesstracker.event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public EventRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Event event) {
        entityManager.persist(event);
    }

    public Event getById(final Long id) {
        return entityManager.find(Event.class, id);
    }

    public List<?> getAllEventsWithName(String name) {
        return entityManager.createQuery("SELECT event FROM event where event.name = ?1")
                .setParameter(1, name)
                .getResultStream()
                .toList();
    }
}
