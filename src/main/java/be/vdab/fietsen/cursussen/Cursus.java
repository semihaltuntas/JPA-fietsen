package be.vdab.fietsen.cursussen;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name = "soort")
// @Inheritance(strategy = InheritanceType.JOINED) // Bu strateji, her sınıf için ayrı tablolar oluşturur ve alt sınıf tabloları, üst sınıf tablosuna foreign key ile bağlanır.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //Her alt sınıf ıcın bir tablo olusturur
@Table(name = "cursussen")

abstract class Cursus {
    @Id
    private UUID id;
    private String naam;

    public UUID getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
