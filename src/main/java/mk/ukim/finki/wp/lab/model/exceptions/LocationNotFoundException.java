package mk.ukim.finki.wp.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocationNotFoundException  extends RuntimeException {
    public LocationNotFoundException(final Long id) {
        super(String.format("Location with id %d not found", id));
    }
}
