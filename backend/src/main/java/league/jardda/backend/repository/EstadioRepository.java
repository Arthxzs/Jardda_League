package league.jardda.backend.repository;

import league.jardda.backend.entity.Cidade;
import league.jardda.backend.entity.Estadio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadioRepository extends JpaRepository<Estadio, Long> {
}
