package be.vdab.fietsen.cursussen;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@DiscriminatorValue("I") //InheritanceType.SINGLE_TABLE Tablolar ıcın gecerlı sadece..
@Table(name = "individuelecursussen")
public class IndividueleCursus extends Cursus{
    private int duurtijd;

    public int getDuurtijd() {
        return duurtijd;
    }
}
