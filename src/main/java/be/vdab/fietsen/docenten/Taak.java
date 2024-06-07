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
    @JoinTable(name = "docententaken", joinColumns = @JoinColumn(name = "taakId"), inverseJoinColumns = @JoinColumn(name = "docentId"))
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
