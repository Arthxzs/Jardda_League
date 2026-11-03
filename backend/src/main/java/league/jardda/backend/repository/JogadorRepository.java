package league.jardda.backend.repository;

import league.jardda.backend.dataTransferObj.JogadorDTO;
import league.jardda.backend.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    List<Jogador> findByNomeJogador(String nomeJogador);
}
