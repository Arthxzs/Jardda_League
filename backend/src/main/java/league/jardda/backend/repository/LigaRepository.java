package league.jardda.backend.repository;

import league.jardda.backend.entity.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LigaRepository extends JpaRepository<Liga, Long> {
    @Query("""
            SELECT l FROM Liga l
            LEFT JOIN FETCH l.paisLiga p
            WHERE (CAST(:nome AS string) IS NULL
                   OR LOWER(l.nomeLiga) LIKE LOWER(CONCAT('%', CAST(:nome AS string), '%')))
              AND (CAST(:pais AS string) IS NULL
                   OR LOWER(p.nomePais) = LOWER(CAST(:pais AS string)))
              AND (CAST(:paisId AS long) IS NULL OR p.paisId = :paisId)
            ORDER BY l.nomeLiga
            """)
    List<Liga> filtrar(@Param("nome") String nome,
                       @Param("pais") String pais,
                       @Param("paisId") Long paisId);
}
