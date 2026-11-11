package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.TecnicoDTO;
import league.jardda.backend.entity.Tecnico;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.TecnicoRepository;
import league.jardda.backend.utils.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;
    private final ParameterValidator parameterValidator;

    private TecnicoDTO toDTO(Tecnico tecnico) {
        return new TecnicoDTO(
                tecnico.getTecnicoId(),
                tecnico.getNomeTecnico(),
                tecnico.getAniversarioTecnico(),
                tecnico.getPaisTecnico().getNomePais()
        );
    }

    public List<TecnicoDTO> listarTecnico(String nome, String pais, Long paisId) {
        parameterValidator.validarPais(paisId);

        List<TecnicoDTO> tecnicos = tecnicoRepository.filtrar(nome, pais, paisId)
                .stream().map(this::toDTO).toList();

        if (tecnicos.isEmpty()) {
            throw new NotFoundException("Nenhum técnico encontrado para os filtros informados");
        }
        return tecnicos;
    }

    public TecnicoDTO buscarPorId(long id) {
        return tecnicoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Técnico com id " + id + " não encontrado"));
    }
}
