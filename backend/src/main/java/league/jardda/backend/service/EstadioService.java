package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.EstadioDTO;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.EstadioRepository;
import league.jardda.backend.utils.StringFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadioService {
    private final EstadioRepository estadioRepository;

    public List<EstadioDTO> listEstadio(String nomeEstadio, String clubeEstadio) {
        if (nomeEstadio == null && clubeEstadio == null) {
            return estadioRepository.findAll()
                    .stream().map(estadio -> new EstadioDTO(
                            estadio.getEstadioId(),
                            estadio.getNomeEstadio(),
                            estadio.getCidadeEstadio().getNomeCidade(),
                            estadio.getClube() == null ? 0 : estadio.getClube().getClubeId(),
                            estadio.getClube() == null ? "Sem clube" : estadio.getClube().getNomeClube()
                    )).toList();
        } else if (nomeEstadio != null && clubeEstadio == null) {
            String finalNomeEstadio = StringFormatter.toCapitalize(nomeEstadio);

            List<EstadioDTO> estadiosEncontrados = estadioRepository.findByNomeEstadio(finalNomeEstadio)
                    .stream().map(estadio -> new EstadioDTO(
                            estadio.getEstadioId(),
                            estadio.getNomeEstadio(),
                            estadio.getCidadeEstadio().getNomeCidade(),
                            estadio.getClube() == null ? 0 : estadio.getClube().getClubeId(),
                            estadio.getClube() == null ? "Sem clube" : estadio.getClube().getNomeClube()
                    )).toList();

            if (estadiosEncontrados.isEmpty()) {
                List<EstadioDTO> estadiosProximosEncontrados = estadioRepository.findAll()
                        .stream()
                        .filter(estadio -> estadio.getNomeEstadio() != null)
                        .filter(estadio -> estadio.getNomeEstadio().contains(finalNomeEstadio))
                        .map(estadio -> new EstadioDTO(
                                estadio.getEstadioId(),
                                estadio.getNomeEstadio(),
                                estadio.getCidadeEstadio().getNomeCidade(),
                                estadio.getClube() == null ? 0 : estadio.getClube().getClubeId(),
                                estadio.getClube() == null ? "Sem clube" : estadio.getClube().getNomeClube()
                        )).toList();
                if (estadiosProximosEncontrados.isEmpty()) {
                    throw new NotFoundException("Estádio '" + nomeEstadio + "' não encontrado");
                } else {
                    return estadiosProximosEncontrados;
                }
            } else {
                return estadiosEncontrados;
            }
        } else {
            String finalClubeEstadio = StringFormatter.toCapitalize(clubeEstadio);

            List<EstadioDTO> estadiosEncontrados = estadioRepository.findAll()
                    .stream()
                    .filter(estadio -> estadio.getClube() != null
                            && estadio.getClube().getNomeClube().equals(finalClubeEstadio))
                    .map(estadio -> new EstadioDTO(
                            estadio.getEstadioId(),
                            estadio.getNomeEstadio(),
                            estadio.getCidadeEstadio().getNomeCidade(),
                            estadio.getClube().getClubeId(),
                            estadio.getClube().getNomeClube()
                    )).toList();

            if (estadiosEncontrados.isEmpty()) {
                throw new NotFoundException("Estádio com time '" + finalClubeEstadio + "' não encontrado");
            } else {
                return estadiosEncontrados;
            }
        }
    }

    public EstadioDTO listEstadioById(long id) {
        try {
            return estadioRepository.findById(id)
                    .stream().map(estadio -> new EstadioDTO(
                            estadio.getEstadioId(),
                            estadio.getNomeEstadio(),
                            estadio.getCidadeEstadio().getNomeCidade(),
                            estadio.getClube() == null ? 0 : estadio.getClube().getClubeId(),
                            estadio.getClube() == null ? "Sem clube" : estadio.getClube().getNomeClube()
                    )).toList().getFirst();
        } catch (RuntimeException e) {
            throw new NotFoundException("Estádio " + id + " não encontrado");
        }
    }
}
