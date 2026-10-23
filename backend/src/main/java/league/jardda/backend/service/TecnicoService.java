package league.jardda.backend.service;

import league.jardda.backend.entity.Cidade;
import league.jardda.backend.entity.Tecnico;
import league.jardda.backend.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;

    public List<Tecnico> listAllTecnico() {
        return tecnicoRepository.findAll();
    }
}
