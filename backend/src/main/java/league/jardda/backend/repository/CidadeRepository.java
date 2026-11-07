package league.jardda.backend.repository;

import league.jardda.backend.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    @Query("""
        SELECT c FROM Cidade c
        JOIN FETCH c.pais p
        WHERE (CAST(:nome AS string) IS NULL
               OR LOWER(c.nomeCidade) LIKE LOWER(CONCAT('%', CAST(:nome AS string), '%')))
          AND (CAST(:pais AS string) IS NULL
               OR LOWER(p.nomePais) = LOWER(CAST(:pais AS string)))
          AND (CAST(:paisId AS long) IS NULL OR p.paisId = :paisId)
        ORDER BY c.nomeCidade
        """)
    List<Cidade> filtrar(@Param("nome") String nome,
                         @Param("pais") String pais,
                         @Param("paisId") Long paisId);
}
