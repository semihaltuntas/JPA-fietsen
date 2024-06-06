package be.vdab.fietsen.campussen;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CampusHeeftDezeDocentAlException extends RuntimeException {
    public CampusHeeftDezeDocentAlException() {
        super("Campus heeft al deze docent.!");
    }
}
