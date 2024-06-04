package be.vdab.fietsen.docenten;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CampusInDocentNietGevondenException extends RuntimeException {
    CampusInDocentNietGevondenException(){
        super("Campus niet gevonden.");
    }
}
