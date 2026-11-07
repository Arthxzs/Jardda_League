package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.ClubeDTO;
import league.jardda.backend.entity.Clube;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.ClubeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubeService {
    private final ClubeRepository clubeRepository;

    private ClubeDTO toDTO(Clube clube) {
        return new ClubeDTO(
                clube.getClubeId(),
                clube.getNomeClube(),
                clube.getCidade().getNomeCidade(),
                clube.getCidade().getPais().getNomePais(),
                clube.getCidade().getPais().getSigla()
        );
    }

    public List<ClubeDTO> listClube(String nomeClube, String paisClube, Long cidadeId, Long paisId) {
        List<ClubeDTO> clubes = clubeRepository.filtrar(nomeClube, paisClube, cidadeId, paisId)
                .stream().map(this::toDTO).toList();
        if (clubes.isEmpty()) {
            throw new NotFoundException("Nenhum clube encontrado para os filtros informados");
        }
        return clubes;
    }

    public ClubeDTO listClubeById(long id) {
        return clubeRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Clube " + id + " não encontrado"));
    }
}
