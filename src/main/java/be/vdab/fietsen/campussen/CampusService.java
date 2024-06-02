package be.vdab.fietsen.campussen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CampusService {
    private final CampusRepository campusRepository;

    public CampusService(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    List<Campus> findWestVlaamse() {
        return campusRepository.findByAdres_PostcodeBetweenOrderByAdres_Gemeente(8000, 8999);
    }
}
