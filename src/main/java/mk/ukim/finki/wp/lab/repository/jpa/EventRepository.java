package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqualAndLocationId(String name, double popularityScore, Long locationId);
    List<Event> findAllByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(String name, double popularityScore);
    List<Event> findAllByLocationId(Long locationId);
}
