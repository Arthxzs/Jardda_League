package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.EstadioDTO;
import league.jardda.backend.entity.Estadio;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.EstadioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadioService {
    private final EstadioRepository estadioRepository;

    private EstadioDTO toDTO(Estadio estadio) {
        return new EstadioDTO(
                estadio.getEstadioId(),
                estadio.getNomeEstadio(),
                estadio.getCidadeEstadio().getNomeCidade(),
                estadio.getClube() == null ? 0 : estadio.getClube().getClubeId(),
                estadio.getClube() == null ? "Sem clube" : estadio.getClube().getNomeClube()
        );
    }

    public List<EstadioDTO> listEstadio(String nomeEstadio, String clubeEstadio, Long cidadeId, Long clubeId) {
        List<EstadioDTO> estadios = estadioRepository.filtrar(nomeEstadio, clubeEstadio, cidadeId, clubeId)
                .stream().map(this::toDTO).toList();
        if (estadios.isEmpty()) {
            throw new NotFoundException("Nenhum estádio encontrado para os filtros informados");
        }
        return estadios;
    }

    public EstadioDTO listEstadioById(long id) {
        return estadioRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Estádio " + id + " não encontrado"));
    }
}
