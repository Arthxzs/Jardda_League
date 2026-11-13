package league.jardda.backend.repository;

import league.jardda.backend.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    @Query("""
            SELECT j FROM Jogador j
            JOIN FETCH j.paisJogador p
            WHERE (CAST(:nome AS string) IS NULL
                   OR LOWER(j.nomeJogador) LIKE LOWER(CONCAT('%', CAST(:nome AS string), '%')))
              AND (CAST(:pais AS string) IS NULL
                   OR LOWER(p.nomePais) = LOWER(CAST(:pais AS string)))
              AND (CAST(:paisId AS long) IS NULL OR p.paisId = :paisId)
            ORDER BY j.nomeJogador
            """)
    List<Jogador> filtrar(@Param("nome") String nome,
                          @Param("pais") String pais,
                          @Param("paisId") Long paisId);
}
