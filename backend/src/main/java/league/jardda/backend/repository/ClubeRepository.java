package league.jardda.backend.repository;

import league.jardda.backend.entity.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClubeRepository extends JpaRepository<Clube, Long> {
    List<Clube> findByNomeClube(String nomeClube);
}
