package be.vdab.fietsen.campussen;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;

@Entity //JPA entity -> Bir Tabloya karsılık gelir
@Table(name = "campussen") //Tablonun adı.
public class Campus {
    @Id // Primary Key
    private long id;
    private String naam;
    @Embedded //@Embedded anotasyonu, adres alanının Adres sınıfına ait bir value object olduğunu belirtir
    private Adres adres;

    protected Campus() {}

    public Campus(long id, String naam, Adres adres) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Adres getAdres() {
        return adres;
    }
}
