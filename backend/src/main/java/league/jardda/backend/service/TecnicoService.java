package league.jardda.backend.service;

import league.jardda.backend.entity.Cidade;
import league.jardda.backend.entity.Tecnico;
import league.jardda.backend.exception.NotFoundException;
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

    public Tecnico listTecnicoById(long id) {
        return tecnicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Técnico " + id + " não encontrado"));
    }
}
