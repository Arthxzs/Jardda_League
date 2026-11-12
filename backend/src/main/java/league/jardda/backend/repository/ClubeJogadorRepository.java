package league.jardda.backend.repository;

import league.jardda.backend.entity.ClubeJogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubeJogadorRepository extends JpaRepository<ClubeJogador, Long> {

    @Query("""
            SELECT cj FROM ClubeJogador cj
            JOIN FETCH cj.jogador
            JOIN FETCH cj.clube
            WHERE cj.clube.clubeId = :clubeId
            AND (:ativo = false OR cj.terminoContratoJogador IS NULL)
            ORDER BY cj.inicioContratoJogador DESC
          """)
    List<ClubeJogador> buscarPorClube(
            @Param("clubeId") Long clubeId,
            @Param("ativo") boolean apenasAtivos
    );
}
