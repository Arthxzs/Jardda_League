package league.jardda.backend.repository;

import league.jardda.backend.entity.ClubeLiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubeLigaRepository extends JpaRepository<ClubeLiga, Long> {

    @Query("""
            SELECT cl FROM ClubeLiga cl
            JOIN FETCH cl.clube
            JOIN FETCH cl.liga
            WHERE cl.clube.clubeId = :clubeId
            AND (:ativo = false OR cl.periodoSaida IS NULL)
            ORDER BY cl.periodoEntrada DESC
          """)
    List<ClubeLiga> buscarPorClube(@Param("clubeId") Long clubeId,
                                   @Param("ativo") boolean apenasAtivos);

    @Query("""
            SELECT cl FROM ClubeLiga cl
            JOIN FETCH cl.clube
            JOIN FETCH cl.liga
            WHERE cl.liga.ligaId = :ligaId
            AND (:ativo = false OR cl.periodoSaida IS NULL)
            ORDER BY cl.periodoEntrada DESC
          """)
    List<ClubeLiga> buscarPorLiga(@Param("ligaId") Long ligaId,
                                  @Param("ativo") boolean apenasAtivos);
}
