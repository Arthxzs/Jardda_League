package league.jardda.backend.repository;

import league.jardda.backend.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    @Query("""
            SELECT p FROM Partida p
            JOIN FETCH p.clube1 c1
            JOIN FETCH p.clube2 c2
            JOIN FETCH p.ligaPartida l
            LEFT JOIN FETCH p.estadioPartida e
            WHERE (CAST(:clubeCasa AS string) IS NULL
                   OR LOWER(c1.nomeClube) = LOWER(CAST(:clubeCasa AS string)))
              AND (CAST(:clubeFora AS string) IS NULL
                   OR LOWER(c2.nomeClube) = LOWER(CAST(:clubeFora AS string)))
              AND (CAST(:rodada AS integer) IS NULL
                   OR p.rodadaPartida = :rodada)
              AND (CAST(:liga AS string) IS NULL
                   OR LOWER(l.nomeLiga) LIKE LOWER(CONCAT('%', CAST(:liga AS string), '%')))
              AND (CAST(:estadio AS string) IS NULL
                   OR LOWER(e.nomeEstadio) LIKE LOWER(CONCAT('%', CAST(:estadio AS string), '%')))
            ORDER BY p.dataPartida
            """)
    List<Partida> filtrar(@Param("clubeCasa") String clubeCasa,
                          @Param("clubeFora") String clubeFora,
                          @Param("rodada") Integer rodada,
                          @Param("liga") String liga,
                          @Param("estadio") String estadioPartida
    );
}
