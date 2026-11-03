package league.jardda.backend.repository;

import league.jardda.backend.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
    List<Tecnico> findByNomeTecnico(String nomeTecnico);
}
