package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.EstadioDTO;
import league.jardda.backend.entity.Estadio;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.EstadioRepository;
import league.jardda.backend.utils.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadioService {
    private final EstadioRepository estadioRepository;
    private final ParameterValidator parameterValidator;

    private EstadioDTO toDTO(Estadio estadio) {
        return new EstadioDTO(
                estadio.getEstadioId(),
                estadio.getNomeEstadio(),
                estadio.getCidadeEstadio().getNomeCidade(),
                estadio.getClube() == null ? null : estadio.getClube().getClubeId(),
                estadio.getClube() == null ? null : estadio.getClube().getNomeClube()
        );
    }

    public List<EstadioDTO> listarEstadio(String nome, String clube, Long cidadeId, Long clubeId) {
        parameterValidator.validarCidade(cidadeId);
        parameterValidator.validarClube(clubeId);

        List<EstadioDTO> estadios = estadioRepository.filtrar(nome, clube, cidadeId, clubeId)
                .stream().map(this::toDTO).toList();

        if (estadios.isEmpty()) {
            throw new NotFoundException("Nenhum estádio encontrado para os filtros informados.");
        }
        return estadios;
    }

    public EstadioDTO buscarPorId(Long id) {
        return estadioRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Estádio com id " + id + " não encontrado."));
    }
}
