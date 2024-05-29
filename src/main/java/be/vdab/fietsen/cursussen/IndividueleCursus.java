package be.vdab.fietsen.cursussen;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("I")
public class IndividueleCursus extends Cursus{
    private int duurtijd;

    public int getDuurtijd() {
        return duurtijd;
    }
}
