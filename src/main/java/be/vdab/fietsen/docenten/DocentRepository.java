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

    //Docent sınıfına alias olarak d veriyoruz. d alias'ını tanımladıktan sonra,
    // bu alias'ı kullanarak entity'nin alanlarına erişebiliriz. (örneğin, d.wedde).
    @Query(" SELECT d from Docent d where d.wedde = (select max (dd.wedde) from Docent dd)")
    List<Docent> findMetGrootsteWedde();

}
