package be.vdab.fietsen.docenten;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DocentHeeftDezeTaakAlException extends RuntimeException {
    DocentHeeftDezeTaakAlException(){
        super("Docent heeft deze taak al.");
    }
}
