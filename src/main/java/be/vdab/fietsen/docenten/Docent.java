package be.vdab.fietsen.docenten;

import be.vdab.fietsen.campussen.Campus;
import be.vdab.fietsen.campussen.CampusService;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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
    @Version
    //@Version anotasyonu,Optimistic JPA'ya bu alanın versiyon bilgisi tutacağını belirtir. Her değişiklik yapıldığında bu alanın değeri otomatik olarak artırılır.
    private long versie;
    @ElementCollection // Bu anotasyon, bu alanın bir koleksiyon value object'ler içerdiğini belirtir.
    @CollectionTable(name = "bijnamen", //Koleksiyonun saklanacağı tablo adını belirtir.
            joinColumns = @JoinColumn(name = "docentId"))
//  docentId kolonunun docenten tablosundaki id kolonu ile ilişkilendirildiğini belirtir.
    @Column(name = "bijnaam") //name = "bijnaam": Takma adların saklanacağı kolon adını belirtir.
    private Set<String> bijnamen;

    @ManyToOne(optional = false, fetch = FetchType.LAZY) //Yani campusId kolonunun doldurulması zorunludur.
    //Eager default campus nesnesini hemen yükler!
    // Lazy ->JPA, Docent nesnesini yüklerken ilişkili Campus nesnesini hemen yüklemez. İlişkili nesneye gerçekten erişildiğinde yüklenir. Bu, performansı iyileştirir.
    @JoinColumn(name = "campusId") // campusId kolonuna referans verir
    private Campus campus;

    @ManyToMany(mappedBy = "docenten")
    //Bu durumda, ilişkiyi yönetmek için bir tarafı seçmiş olursunuz ve JPA diğer tarafı otomatik olarak bulur.
    @OrderBy("naam")
    private Set<Taak> taken;


    protected Docent() { //JPA tarafından kullanılmak üzere gerekli olan parametresiz constructor'dır. Bu constructor'ın korumalı (protected) olması,
        // sınıf dışındaki kodun bu constructor'ı çağırarak eksik bilgiyle Docent nesnesi oluşturmasını engeller.
    }

    public Docent(String voornaam, String familienaam, BigDecimal wedde, String emailAdres, Geslacht geslacht, Campus campus) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.wedde = wedde;
        this.emailAdres = emailAdres;
        this.geslacht = geslacht;
        bijnamen = new LinkedHashSet<>();
        this.campus = campus;
        taken = new LinkedHashSet<>();
    }

    void add(Taak taak) {
        if (!taken.add(taak)) {
            throw new DocentHeeftDezeTaakAlException();
        }
    }

    public Set<Taak> getTaken() {
        return Collections.unmodifiableSet(taken);
    }

    void voegBijnaamToe(String bijnaam) {
        if (!bijnamen.add(bijnaam)) {
            throw new DocentHeeftDezeBijnaamAlException(); //bir öge zaten varsa false döner ve hata fırlatır.
        }
    }

    void verwijderBijnaam(String bijnaam) {
        bijnamen.remove(bijnaam); //Koleksiyondan cıkartır
    }

    public Set<String> getBijnamen() {
        //return bijnamen; eğer bunu yazarsak koleysiyon üzerinde dışardan degısıklık yapılabılır.
        return Collections.unmodifiableSet(bijnamen);
        //koleksiyonun değiştirilemez bir kopyasını döner. Böylece, dışarıdan bu koleksiyon üzerinde değişiklik yapılması engellenir.
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Docent docent &&
                emailAdres.equalsIgnoreCase(docent.emailAdres);
        // in plaats van id ,moeten we gebruik hier emailAdres als uniek.!
        // elke docent heeft een uniek email adres.
    }

    @Override
    public int hashCode() {
        return emailAdres.toLowerCase().hashCode();
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

    public void setWedde(BigDecimal wedde) {
        this.wedde = wedde;
    }

    public Campus getCampus() {
        return campus;
    }
}
