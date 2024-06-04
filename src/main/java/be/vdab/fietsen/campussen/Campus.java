package be.vdab.fietsen.campussen;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity //JPA entity -> Bir Tabloya karsılık gelir
@Table(name = "campussen") //Tablonun adı.
public class Campus {
    @Id // Primary Key
    private long id;
    private String naam;
    @Embedded //@Embedded anotasyonu, adres alanının Adres sınıfına ait bir value object olduğunu belirtir
    private Adres adres;

   @ElementCollection
   @CollectionTable(name = "huurprijzen",joinColumns = @JoinColumn(name = "campusId"))
   @OrderBy("vanaf")
    private Set<Huurprijs> huurprijzen;

    protected Campus() {}

    public Campus(long id, String naam, Adres adres) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
        huurprijzen = new LinkedHashSet<>();
    }
    public  Set<Huurprijs> getHuurprijzen(){
        return Collections.unmodifiableSet(huurprijzen);
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
