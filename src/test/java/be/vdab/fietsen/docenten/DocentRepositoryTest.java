package be.vdab.fietsen.docenten;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class DocentRepositoryTest {
    private static final String DOCENTEN_TABLE = "docenten";
    private final DocentRepository docentRepository;
    private final JdbcClient jdbcClient;

    public DocentRepositoryTest(DocentRepository docentRepository, JdbcClient jdbcClient) {
        this.docentRepository = docentRepository;
        this.jdbcClient = jdbcClient;
    }

    @Test
    void countVindtHetJuisteAantalRecords() {
        assertThat(docentRepository.count())
                .isEqualTo(JdbcTestUtils.countRowsInTable(jdbcClient, DOCENTEN_TABLE));
    }
}