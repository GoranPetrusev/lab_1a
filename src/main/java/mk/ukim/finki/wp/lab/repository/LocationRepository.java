package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {
    public List<Location> findAll() {
        return DataHolder.locations;
    }
    public Optional<Location> findById(Long id) {
        return DataHolder.locations.stream()
            .filter(l -> l.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.events.removeIf(e -> e.getLocation().getId().equals(id));
        DataHolder.locations.removeIf(l -> l.getId().equals(id));
    }

    public void save(String name, String description, String address, String capacity, String locationId) {
        Location location = DataHolder.locations.stream().filter(l -> l.getId().equals(locationId)).findFirst().orElse(null);

        location.setName(name);
        location.setDescription(description);
        location.setCapacity(capacity);
        location.setAddress(address);
    }

    public void save(String name, String address, String capacity, String description) {
        DataHolder.locations.add(new Location(name, address, capacity, description));
    }
}
