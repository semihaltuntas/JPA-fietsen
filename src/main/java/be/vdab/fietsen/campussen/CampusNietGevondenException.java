package be.vdab.fietsen.campussen;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CampusNietGevondenException extends RuntimeException {
    public CampusNietGevondenException() {
        super("Campus niet gevonden!");
    }
}
