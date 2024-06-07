package be.vdab.fietsen.docenten;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "taken")
public class Taak {
    @Id
    private long id;
    private String naam;
    @ManyToMany
    // "Taak" ve "Docent" varlıkları arasında çoktan çoğa bir ilişkiyi temsil ettiğini belirtir
    //Bu ilişkiyi yönetmek için bir ara tablo (join table) kullanılır.
    @JoinTable(name = "docententaken",
            joinColumns = @JoinColumn(name = "taakId"),//taken tablosunun primaryKey i gösterir
            inverseJoinColumns = @JoinColumn(name = "docentId"))//docenten tablosundakı PKey i gösterir
    private Set<Docent> docenten;

    void add(Docent docent) {
        if (!docenten.add(docent)) {
            throw new DocentHeeftDezeTaakAlException();
        }
    }

    Set<Docent> getDocenten() {
        return Collections.unmodifiableSet(docenten);
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taak taak = (Taak) o;
        return naam != null && naam.equalsIgnoreCase(taak.naam);
    }

    @Override
    public int hashCode() {
        return naam.toLowerCase().hashCode();
    }
}
