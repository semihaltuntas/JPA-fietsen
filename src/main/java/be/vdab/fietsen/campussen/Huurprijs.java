package be.vdab.fietsen.campussen;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class Huurprijs {
    private LocalDate vanaf;
    private BigDecimal prijs;

    public LocalDate getVanaf() {
        return vanaf;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    @Override
    public boolean equals(Object object) {
       return object instanceof Huurprijs huurprijs && vanaf.equals(huurprijs.vanaf);
    }

    @Override
    public int hashCode() {
        return vanaf.hashCode();
    }
}
