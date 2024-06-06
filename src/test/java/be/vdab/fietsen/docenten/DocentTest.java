package be.vdab.fietsen.docenten;

import be.vdab.fietsen.campussen.Adres;
import be.vdab.fietsen.campussen.Campus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class DocentTest {
    @Test
    void jeKanMeerdereDocentenToevoegenAanEenCampus() {
        var adres = new Adres("test", "test", 1000, "test");
        var campus = new Campus("test", adres);
        var docent = new Docent("test1", "test1", BigDecimal.TEN, "test1.test1@example.org", Geslacht.MAN, campus);
        // System.out.println(docent.getId()); = 0
        campus.voegDocentToe(docent);
        var andereDocent = new Docent("test2", "test2", BigDecimal.TEN, "test2.test2@example.org", Geslacht.MAN, campus);
        // System.out.println(andereDocent.getId()); = 0
        campus.voegDocentToe(andereDocent);
        //Çünkü id değeri equals metodunda kullanıldığı için,
        // Set ikinci Docent nesnesini ilkine eşit olarak değerlendirir ve eklemeyi reddeder.
    }

    @Test
    void nadatJeEenDocentOpslaatBehoortHijNogTotZijnCampus() {
        var adres = new Adres("test", "test", 1000, "test");
        var campus = new Campus("test", adres);
        var docent = new Docent("test1", "test1", BigDecimal.TEN, "test1.test1@example.org", Geslacht.MAN, campus);
        campus.voegDocentToe(docent); ReflectionTestUtils.setField(docent, "id", 1);
        //Veritabanına kaydedildiğinde id değeri 1 olarak değiştiriliyor.
        assertThat(campus.getDocenten().contains(docent)).isTrue(); }
}