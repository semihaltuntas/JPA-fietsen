package be.vdab.fietsen.cursussen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CursusService {
    private final CursusRepository cursusRepository;

    public CursusService(CursusRepository cursusRepository) {
        this.cursusRepository = cursusRepository;
    }

    List<Cursus> findAll() {
        return cursusRepository.findAll();
    }
    List<GroupsCursus> findGroupsCursussen(){
        return cursusRepository.findGroupsCursussen();
    }
    List<IndividueleCursus> findIndividueleCursussen(){
        return cursusRepository.findIndividueleCursussen();
    }
}
