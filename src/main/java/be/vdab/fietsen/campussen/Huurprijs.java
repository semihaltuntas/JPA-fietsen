package be.vdab.fietsen.campussen;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class Huurprijs {

    /*private long campusId; -> je mag aan de class Huurprijs geen private variabele
    toevoegen die hoort bij de foreign key kolom (campusId) in de table huuprijzen.. exception werpt */
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
        /*Huurprijs nesnesinin başka bir Huurprijs nesnesiyle eşit olup olmadığını kontrol eder.
         Bu metod, yalnızca vanaf tarihine dayalı bir karşılaştırma yapar:*/
       return object instanceof Huurprijs huurprijs && vanaf.equals(huurprijs.vanaf);
    }

    @Override
    public int hashCode() {
        /*Huurprijs nesnesi için bir hash kodu döner.
         Bu hash kodu, yine vanaf tarihine dayalı olarak hesaplanır:*/
        return vanaf.hashCode();
    }
}
