package be.vdab.fietsen.campussen;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("campussen")
public class CampusController {
    private final CampusService campusService;

    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    private record CampusBeknopt(long id, String naam, Adres adres) {
        CampusBeknopt(Campus campus) {
            this(campus.getId(), campus.getNaam(), campus.getAdres());
        }
    }

    @GetMapping("westvlaams")
    Stream<CampusBeknopt> findWestVlaamse() {
        return campusService.findWestVlaamse()
                .stream()
                .map(CampusBeknopt::new);
    }
}
