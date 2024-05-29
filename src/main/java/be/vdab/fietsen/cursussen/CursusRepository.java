package be.vdab.fietsen.cursussen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursusRepository extends JpaRepository<Cursus,Long> {
    List<Cursus> findAll();
    @Query("select g from GroupsCursus g")
    List<GroupsCursus> findGroupsCursussen();

    @Query("select i from IndividueleCursus i")
    List<IndividueleCursus> findIndividueleCursussen();
}
