package be.vdab.fietsen.cursussen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cursussen")
public class CursusController {
    private final CursusService cursusService;

    public CursusController(CursusService cursusService) {
        this.cursusService = cursusService;
    }

    @GetMapping()
    List<Cursus> findAllCursussen() {
        return cursusService.findAll();
    }

    @GetMapping("groep")
    List<GroupsCursus> findGroupsCursussen() {
        return cursusService.findGroupsCursussen();
    }

    @GetMapping("individuele")
    List<IndividueleCursus> findIndividueleCursussen() {
        return cursusService.findIndividueleCursussen();
    }
}
