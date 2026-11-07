package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.LigaDTO;
import league.jardda.backend.entity.Liga;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.LigaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LigaService {
    private final LigaRepository ligaRepository;

    private LigaDTO toDTO(Liga liga) {
        return new LigaDTO(
                liga.getLigaId(),
                liga.getNomeLiga(),
                liga.getPaisLiga() == null ? "indefinido" : liga.getPaisLiga().getNomePais(),
                liga.getEstiloLiga(),
                liga.getCategoriaLiga()
        );
    }

    public List<LigaDTO> listLiga(String nomeLiga, String paisLiga, Long paisId) {
        List<LigaDTO> ligas = ligaRepository.filtrar(nomeLiga, paisLiga, paisId)
                .stream().map(this::toDTO).toList();
        if (ligas.isEmpty()) {
            throw new NotFoundException("Nenhuma liga encontrada para os filtros informados");
        }
        return ligas;
    }

    public LigaDTO listLigaById(long id) {
        return ligaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Liga " + id + " não encontrada"));
    }
}
