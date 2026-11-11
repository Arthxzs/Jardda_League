package league.jardda.backend.repository;

import league.jardda.backend.entity.ClubeTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubeTecnicoRepository extends JpaRepository<ClubeTecnico, Long> {

    @Query("""
            SELECT ct FROM ClubeTecnico ct
            JOIN FETCH ct.clube
            JOIN FETCH ct.tecnico
            WHERE ct.clube.clubeId = :clubeId
            AND (:ativo = false OR ct.terminoContratoTecnico IS NULL)
          """)
    List<ClubeTecnico> buscarPorClube(@Param("clubeId") Long clubeId,
                                      @Param("ativo") boolean apenasAtivos);

    @Query("""
            SELECT ct FROM ClubeTecnico ct
            JOIN FETCH ct.clube
            JOIN FETCH ct.tecnico
            WHERE ct.tecnico.tecnicoId = :tecnicoId
            AND (:ativo = false OR ct.terminoContratoTecnico IS NULL)
          """)
    List<ClubeTecnico> buscarPorTecnico(@Param("tecnicoId") Long tecnicoId,
                                        @Param("ativo") boolean apenasAtivos);
}
