package be.vdab.fietsen.docenten;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "docenten")
public class Docent {

    @Id //PrimaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Otomatik numralandırma olusturur yenı bır record eklerken
    private long id;
    private String voornaam;
    private String familienaam;
    private BigDecimal wedde;
    private String emailAdres;
    @Enumerated(EnumType.STRING) //Bu, JPA'ya enum değerlerini veritabanında string olarak saklamasını söyler.
    private Geslacht geslacht;

    protected Docent() { //JPA tarafından kullanılmak üzere gerekli olan parametresiz constructor'dır. Bu constructor'ın korumalı (protected) olması,
        // sınıf dışındaki kodun bu constructor'ı çağırarak eksik bilgiyle Docent nesnesi oluşturmasını engeller.
    }

    public Docent(String voornaam, String familienaam, BigDecimal wedde, String emailAdres, Geslacht geslacht) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.wedde = wedde;
        this.emailAdres = emailAdres;
        this.geslacht = geslacht;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public BigDecimal getWedde() {
        return wedde;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }
}
