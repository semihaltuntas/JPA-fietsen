package be.vdab.fietsen.campussen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampusRepository extends JpaRepository<Campus,Long> {
    List<Campus> findByAdres_PostcodeBetweenOrderByAdres_Gemeente(int van,int tot);
}
