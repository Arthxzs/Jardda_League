package league.jardda.backend.service;

import league.jardda.backend.entity.Cidade;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CidadeService {
    private final CidadeRepository cidadeRepository;

    public List<Cidade> listAllCidade() {
        return cidadeRepository.findAll();
    }

    public Cidade listCidadeById(long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cidade " + id + " não encontrada."));
    }
}
