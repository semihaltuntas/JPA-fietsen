package be.vdab.fietsen.docenten;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.print.Doc;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DocentRepository extends JpaRepository<Docent, Long> {
    List<Docent> findByWeddeOrderByFamilienaam(BigDecimal wedde);

    Optional<Docent> findByEmailAdres(String emailAdres);

    int countByWedde(BigDecimal wedde);

    @Query(" SELECT d from Docent d where d.wedde = (select max (dd.wedde) from Docent dd)")
        //Docent sınıfına alias olarak d veriyoruz. d alias'ını tanımladıktan sonra,
        // bu alias'ı kullanarak entity'nin alanlarına erişebiliriz. (örneğin, d.wedde).
    List<Docent> findMetGrootsteWedde();

    @Query("select max(d.wedde) from Docent d")
    BigDecimal findGrootsteWedde();

    @Query("select d.voornaam as voornaam, d.familienaam as familienaam from Docent d order by d.voornaam , d.familienaam")
        // Interface kullanarak sadece gerekli entitiyleri kullanma
    List<EnkelNaam> findNamen();

    @Query("select d.wedde as wedde,count(d) as aantal from Docent d group by d.wedde")
    //Interface kullanarak gerekli maasları alias kullanarak dönderme..
    List<AantalDocentenPerWedde> findAantalDocentenPerWedde();
}
