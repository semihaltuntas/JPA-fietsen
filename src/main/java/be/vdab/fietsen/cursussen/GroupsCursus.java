package be.vdab.fietsen.cursussen;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
// @DiscriminatorValue("G") // InheritanceType.SINGLE_TABLE Tablolar ıcın gecerlı sadece..
@Table(name ="groepscursussen")
public class GroupsCursus extends Cursus{
    private LocalDate van;
    private LocalDate tot;

    public LocalDate getVan() {
        return van;
    }

    public LocalDate getTot() {
        return tot;
    }
}
