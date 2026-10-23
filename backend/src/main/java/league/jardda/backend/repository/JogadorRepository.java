package league.jardda.backend.repository;

import league.jardda.backend.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
