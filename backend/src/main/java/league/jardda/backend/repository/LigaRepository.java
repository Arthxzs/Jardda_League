package league.jardda.backend.repository;

import league.jardda.backend.entity.Liga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigaRepository extends JpaRepository<Liga, Long> {
    List<Liga> findByNomeLiga(String nomeLiga);
}
