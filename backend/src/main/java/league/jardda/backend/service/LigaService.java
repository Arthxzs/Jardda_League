package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.LigaDTO;
import league.jardda.backend.entity.Liga;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.LigaRepository;
import league.jardda.backend.utils.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LigaService {
    private final LigaRepository ligaRepository;
    private final ParameterValidator parameterValidator;

    private LigaDTO toDTO(Liga liga) {
        return new LigaDTO(
                liga.getLigaId(),
                liga.getNomeLiga(),
                liga.getPaisLiga() == null ? null : liga.getPaisLiga().getNomePais(),
                liga.getEstiloLiga(),
                liga.getCategoriaLiga()
        );
    }

    public List<LigaDTO> listarLiga(String nome, String pais, Long paisId) {
        parameterValidator.validarPais(paisId);

        List<LigaDTO> ligas = ligaRepository.filtrar(nome, pais, paisId)
                .stream().map(this::toDTO).toList();

        if (ligas.isEmpty()) {
            throw new NotFoundException("Nenhuma liga encontrada para os filtros informados.");
        }
        return ligas;
    }

    public LigaDTO buscarPorId(Long id) {
        return ligaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Liga com id " + id + " não encontrada."));
    }
}
