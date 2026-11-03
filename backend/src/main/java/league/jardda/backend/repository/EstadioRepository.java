package league.jardda.backend.repository;

import league.jardda.backend.entity.Cidade;
import league.jardda.backend.entity.Estadio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadioRepository extends JpaRepository<Estadio, Long> {
    List<Estadio> findByNomeEstadio(String nomeEstadio);
}
