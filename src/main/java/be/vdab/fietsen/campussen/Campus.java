package be.vdab.fietsen.campussen;

import be.vdab.fietsen.docenten.Docent;
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
    @CollectionTable(name = "huurprijzen", joinColumns = @JoinColumn(name = "campusId"))
    @OrderBy("vanaf")
    private Set<Huurprijs> huurprijzen;

    @OneToMany(mappedBy = "campus") // Campus sınıfında docenten değişkeninin birden çok Docent nesnesini temsil ettiğini belirtir.
    // mappedBy = "campus" ifadesi, Docent sınıfındaki campus değişkeniyle bu ilişkinin karşı tarafını işaret eder.
    @OrderBy("voornaam,familienaam")
    private Set<Docent> docenten;

    public Set<Docent> getDocenten() {
        return Collections.unmodifiableSet(docenten);
    }
    public void voegDocentToe(Docent docent){
        if (!docenten.add(docent)){
            throw new CampusHeeftDezeDocentAlException();
        }
    }

    protected Campus() {
    }

    public Campus(String naam, Adres adres) {
        this.naam = naam;
        this.adres = adres;
        huurprijzen = new LinkedHashSet<Huurprijs>();
        docenten = new LinkedHashSet<Docent>();
    }

    public Set<Huurprijs> getHuurprijzen() {
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
