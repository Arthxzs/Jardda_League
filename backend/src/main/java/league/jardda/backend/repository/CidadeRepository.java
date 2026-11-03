package league.jardda.backend.repository;

import league.jardda.backend.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    List<Cidade> findByNomeCidade(String nomeCidade);
    List<Cidade> findByPais(String paisCidade);
}
