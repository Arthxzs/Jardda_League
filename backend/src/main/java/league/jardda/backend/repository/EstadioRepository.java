package league.jardda.backend.repository;

import league.jardda.backend.entity.Estadio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstadioRepository extends JpaRepository<Estadio, Long> {
    @Query("""
            SELECT e FROM Estadio e
            JOIN FETCH e.cidadeEstadio ci
            LEFT JOIN FETCH e.clube cl
            WHERE (CAST(:nome AS string) IS NULL
                   OR LOWER(e.nomeEstadio) LIKE LOWER(CONCAT('%', CAST(:nome AS string), '%')))
              AND (CAST(:clube AS string) IS NULL
                   OR LOWER(cl.nomeClube) = LOWER(CAST(:clube AS string)))
              AND (CAST(:cidadeId AS long) IS NULL OR ci.cidadeId = :cidadeId)
              AND (CAST(:clubeId AS long) IS NULL OR cl.clubeId = :clubeId)
            ORDER BY e.nomeEstadio
            """)
    List<Estadio> filtrar(@Param("nome") String nome,
                          @Param("clube") String clube,
                          @Param("cidadeId") Long cidadeId,
                          @Param("clubeId") Long clubeId);
}
