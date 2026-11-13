package league.jardda.backend.repository;

import league.jardda.backend.entity.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubeRepository extends JpaRepository<Clube, Long> {
    @Query("""
            SELECT c FROM Clube c
            JOIN FETCH c.cidade ci
            JOIN FETCH ci.pais p
            WHERE (CAST(:nome AS string) IS NULL
                   OR LOWER(c.nomeClube) LIKE LOWER(CONCAT('%', CAST(:nome AS string), '%')))
              AND (CAST(:pais AS string) IS NULL
                   OR LOWER(p.nomePais) = LOWER(CAST(:pais AS string)))
              AND (CAST(:cidadeId AS long) IS NULL OR ci.cidadeId = :cidadeId)
              AND (CAST(:paisId AS long) IS NULL OR p.paisId = :paisId)
            ORDER BY c.nomeClube
            """)
    List<Clube> filtrar(@Param("nome") String nome,
                        @Param("pais") String pais,
                        @Param("cidadeId") Long cidadeId,
                        @Param("paisId") Long paisId);
}
