package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.TecnicoDTO;
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

    private TecnicoDTO toDTO(Tecnico tecnico) {
        return new TecnicoDTO(
                tecnico.getTecnicoId(),
                tecnico.getNomeTecnico(),
                tecnico.getAniversarioTecnico(),
                tecnico.getPaisTecnico().getNomePais()
        );
    }

    public List<TecnicoDTO> listTecnico(String nomeTecnico, String paisTecnico, Long paisId) {
        List<TecnicoDTO> tecnicos = tecnicoRepository.filtrar(nomeTecnico, paisTecnico, paisId)
                .stream().map(this::toDTO).toList();
        if (tecnicos.isEmpty()) {
            throw new NotFoundException("Nenhum técnico encontrado para os filtros informados");
        }
        return tecnicos;
    }

    public TecnicoDTO listTecnicoById(long id) {
        return tecnicoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Técnico " + id + " não encontrado"));
    }
}
