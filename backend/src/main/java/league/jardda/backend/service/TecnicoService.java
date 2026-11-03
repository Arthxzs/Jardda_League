package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.TecnicoDTO;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.TecnicoRepository;
import league.jardda.backend.utils.StringFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;

    public List<TecnicoDTO> listTecnico(String nomeTecnico, String paisTecnico) {
        if (nomeTecnico == null && paisTecnico == null){
            return tecnicoRepository.findAll()
                    .stream().map(tecnico -> new TecnicoDTO(
                            tecnico.getTecnicoId(),
                            tecnico.getNomeTecnico(),
                            tecnico.getAniversarioTecnico(),
                            tecnico.getPaisTecnico().getNomePais()
                    )).toList();
        } else if (nomeTecnico != null && paisTecnico == null){
            String finalNomeTecnico = StringFormatter.toCapitalize(nomeTecnico);

            List<TecnicoDTO> tecnicosEncontrados = tecnicoRepository.findByNomeTecnico(finalNomeTecnico)
                    .stream().map(tecnico -> new TecnicoDTO(
                            tecnico.getTecnicoId(),
                            tecnico.getNomeTecnico(),
                            tecnico.getAniversarioTecnico(),
                            tecnico.getPaisTecnico().getNomePais()
                    )).toList();
            if (tecnicosEncontrados.isEmpty()) {
                List<TecnicoDTO> tecnicosProximosEncontrados = tecnicoRepository.findAll()
                        .stream()
                        .filter(tecnico -> tecnico.getNomeTecnico() != null
                                && tecnico.getNomeTecnico().contains(finalNomeTecnico))
                        .map(tecnico -> new TecnicoDTO(
                                tecnico.getTecnicoId(),
                                tecnico.getNomeTecnico(),
                                tecnico.getAniversarioTecnico(),
                                tecnico.getPaisTecnico().getNomePais()
                        )).toList();
                if (tecnicosProximosEncontrados.isEmpty()) {
                    throw new NotFoundException("Técnico '" + nomeTecnico + "' não encontrado");
                } else {
                    return tecnicosProximosEncontrados;
                }
            } else {
                return tecnicosEncontrados;
            }
        } else {
            String finalPaisTecnico = StringFormatter.toCapitalize(paisTecnico);

            List<TecnicoDTO> tecnicosEncontrados = tecnicoRepository.findAll()
                    .stream()
                    .filter(tecnico -> tecnico.getPaisTecnico() != null
                            && tecnico.getPaisTecnico().getNomePais().equals(finalPaisTecnico))
                    .map(tecnico -> new TecnicoDTO(
                            tecnico.getTecnicoId(),
                            tecnico.getNomeTecnico(),
                            tecnico.getAniversarioTecnico(),
                            tecnico.getPaisTecnico().getNomePais()
                    )).toList();
            if (tecnicosEncontrados.isEmpty()) {
                throw new NotFoundException("Técnico com país '" + paisTecnico + "' não encontrado");
            } else {
                return tecnicosEncontrados;
            }
        }
    }

    public TecnicoDTO listTecnicoById(long id) {
        try {
            return tecnicoRepository.findById(id)
                    .stream().map(tecnico -> new TecnicoDTO(
                            tecnico.getTecnicoId(),
                            tecnico.getNomeTecnico(),
                            tecnico.getAniversarioTecnico(),
                            tecnico.getPaisTecnico().getNomePais()
                    )).toList().getFirst();
        } catch (RuntimeException e) {
            throw new NotFoundException("Técnico " + id + " não encontrado");
        }
    }
}
