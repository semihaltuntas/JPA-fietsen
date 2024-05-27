package be.vdab.fietsen.docenten;

import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    @GetMapping
    List<Docent> findAll() {
        return docentService.findAll();
    }

    @GetMapping("{id}")
    Docent findById(@PathVariable long id) {
        return docentService.findById(id).orElseThrow(DocentNietGevondenException::new);
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
    List<Docent> findByWedde(BigDecimal wedde) {
        return docentService.findByWedde(wedde);
    }

    @GetMapping(params = "emailAdres")
    Docent findByEmailAdres(String emailAdres) {
        return docentService.findByEmailAdres(emailAdres)
                .orElseThrow(DocentNietGevondenException::new);
    }

    @GetMapping(value = "aantal", params = "wedde")
    int findAantalMetWedde(BigDecimal wedde) {
        return docentService.findAantalMetWedde(wedde);
    }

    @GetMapping("metGrootsteWedde")
    List<Docent> findMetGrootsteWedde() {
        return docentService.findMetGrootsteWedde();
    }
}
