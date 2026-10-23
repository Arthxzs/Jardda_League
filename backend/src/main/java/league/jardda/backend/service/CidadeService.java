package league.jardda.backend.service;

import league.jardda.backend.entity.Cidade;
import league.jardda.backend.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {
    private final CidadeRepository cidadeRepository;

    public List<Cidade> listAllCidade() {
        return cidadeRepository.findAll();
    }
}
