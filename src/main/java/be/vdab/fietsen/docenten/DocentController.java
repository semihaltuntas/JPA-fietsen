package be.vdab.fietsen.docenten;

import be.vdab.fietsen.campussen.Adres;
import be.vdab.fietsen.campussen.Campus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping("docenten")
public class DocentController {
    private final DocentService docentService;

    public DocentController(DocentService docentService) {
        this.docentService = docentService;
    }

    @GetMapping("aantal")
    long findAantal() {
        return docentService.findAantal();
    }

    // Java Record kullanarak kısa ve öz DTO tanımları yapabilir, bu DTO'ları kullanarak veri tabanından sadece gerekli bilgileri alıp döndürebilirsiniz.
    private record DocentBeknopt(long id, String voornaam, String familienaam) {
        DocentBeknopt(Docent docent) {
            this(docent.getId(), docent.getVoornaam(), docent.getFamilienaam());
        }
    }

    @GetMapping
    Stream<DocentBeknopt> findAll() {
        return docentService.findAll()
                .stream()
                .map(DocentBeknopt::new);
    }

    @GetMapping("{id}")
    DocentBeknoptMetBijnamen findById(@PathVariable long id) {
        return docentService.findById(id)
                .map(DocentBeknoptMetBijnamen::new)
                .orElseThrow(DocentNietGevondenException::new);
    }

    @GetMapping("{id}/emailAdres")
    String findEmailAdresById(@PathVariable long id) {
        return docentService.findById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .getEmailAdres();
    }

    @GetMapping("{id}/bestaat")
    boolean bestaatById(@PathVariable long id) {
        return docentService.existsById(id);
    }

    @PostMapping
    long create(@RequestBody @Valid NieuweDocent nieuweDocent) {
        return docentService.create(nieuweDocent);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        try {
            docentService.deleteById(id);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }

    @GetMapping(params = "wedde")
    Stream<DocentBeknopt> findByWedde(BigDecimal wedde) {
        return docentService.findByWedde(wedde)
                .stream()
                .map(DocentBeknopt::new);
    }

    @GetMapping(params = "emailAdres")
    DocentBeknoptMetBijnamen findByEmailAdres(String emailAdres) {
        return docentService.findByEmailAdres(emailAdres)
                .map(DocentBeknoptMetBijnamen::new)
                .orElseThrow(DocentNietGevondenException::new);
    }

    @GetMapping(value = "aantal", params = "wedde")
    int findAantalMetWedde(BigDecimal wedde) {
        return docentService.findAantalMetWedde(wedde);
    }

    @GetMapping("metGrootsteWedde")
    Stream<DocentBeknopt> findMetGrootsteWedde() {
        return docentService.findMetGrootsteWedde()
                .stream()
                .map(DocentBeknopt::new);
    }

    @GetMapping("weddes/grootste")
    BigDecimal findGrootsteWedde() {
        return docentService.findGrootsteWedde();
    }

    @GetMapping("namen")
    List<EnkelNaam> findNamen() {
        return docentService.findNamen();
    }

    @GetMapping("aantalPerWedde")
    List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
        return docentService.findAantalDocentenPerWedde();
    }

    @PatchMapping("{id}/wedde")
    void wijzigWedde(@PathVariable long id,
                     @RequestBody @NotNull @Positive BigDecimal wedde) {
        try {
            docentService.wijzigWedde(id, wedde);
        } catch (OptimisticLockingFailureException ex) {
            throw new EenAndereGebruikerWijzigdeDeDocentException();
        }
    }

    private record Opslag(@NotNull @Positive BigDecimal bedrag) {
    }

    @PostMapping("weddeverhogingen")
    void algemeneOpslag(@RequestBody @NotNull @Positive BigDecimal bedrag) {
        docentService.algemeneOpslag(bedrag);
    }

    @PostMapping("{id}/bijnamen")
    void voegBijnaamToe(@PathVariable long id,
                        @RequestBody @NotBlank String bijnaam) {
        docentService.voegBijnaamToe(id, bijnaam);
    }

    @DeleteMapping("{id}/bijnamen/{bijnaam}")
    void verwijderBijnaam(@PathVariable long id,
                          @PathVariable String bijnaam) {
        docentService.deleteBijnaam(id, bijnaam);
    }

    private record DocentBeknoptMetBijnamen(long id, String voornaam, String familienaam, Set<String> bijnamen) {
        DocentBeknoptMetBijnamen(Docent docent) {
            this(docent.getId(), docent.getVoornaam(), docent.getFamilienaam(), docent.getBijnamen());
        }
    }

    @GetMapping("metBijnamen")
    Stream<DocentBeknoptMetBijnamen> findAllMetBijnamen() {
        return docentService.findAllMetBijnamen()
                .stream()
                .map(DocentBeknoptMetBijnamen::new);
    }

    private record CampusBeKnopt(long id, String naam, Adres adres) {
        CampusBeKnopt(Campus campus) {
            this(campus.getId(), campus.getNaam(), campus.getAdres());
        }
    }

    @GetMapping("{id}/campus")
    CampusBeKnopt findCampusVan(@PathVariable long id) {
        return docentService.findById(id)
                .map(docent -> new CampusBeKnopt(docent.getCampus()))
                .orElseThrow(DocentNietGevondenException::new);
    }

    private record DocentBeknoptMetCampus(long id, String voornaam,
                                          String familienaam, long campusId, String campusNaam) {
        DocentBeknoptMetCampus(Docent docent) {
            this(docent.getId(), docent.getVoornaam(), docent.getFamilienaam(),
                    docent.getCampus().getId(), docent.getCampus().getNaam());
        }
    }

    @GetMapping("metCampussen")
    Stream<DocentBeknoptMetCampus> findAllMetCampussen() {
        return docentService.findAllMetCampussen()
                .stream()
                .map(docent -> new DocentBeknoptMetCampus(docent));
    }

    private record TaakBeknopt(long id, String naam) {
        TaakBeknopt(Taak taak) {
            this(taak.getId(), taak.getNaam());
        }
    }

    @GetMapping("{id}/taken")
    Stream<TaakBeknopt> findTaken(@PathVariable long id) {
        return docentService.findById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .getTaken()
                .stream()
                .map(taak -> new TaakBeknopt(taak));
    }
}
