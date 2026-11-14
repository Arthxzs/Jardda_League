package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.ClubeLigaDTO;
import league.jardda.backend.entity.ClubeLiga;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.ClubeLigaRepository;
import league.jardda.backend.utils.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubeLigaService {

    private final ClubeLigaRepository clubeLigaRepository;
    private final ParameterValidator parameterValidator;

    public List<ClubeLigaDTO> listarPorClube(Long clubeId, boolean apenasAtivos) {
        parameterValidator.validarClube(clubeId);
        List<ClubeLiga> vinculos = clubeLigaRepository.buscarPorClube(clubeId, apenasAtivos);
        if (vinculos.isEmpty()) {
            throw new NotFoundException("Clube com id " + clubeId + " não possui ligas.");
        }
        return vinculos.stream().map(this::toDTO).toList();
    }

    public List<ClubeLigaDTO> listarPorLiga(Long ligaId, boolean apenasAtivos) {
        parameterValidator.validarLiga(ligaId);
        List<ClubeLiga> vinculos = clubeLigaRepository.buscarPorLiga(ligaId, apenasAtivos);
        if (vinculos.isEmpty()) {
            throw new NotFoundException("Liga com id " + ligaId + " não possui clubes.");
        }
        return vinculos.stream().map(this::toDTO).toList();
    }

    private ClubeLigaDTO toDTO(ClubeLiga cl) {
        return new ClubeLigaDTO(
                cl.getClubeLigaId(),
                cl.getClube().getClubeId(),
                cl.getClube().getNomeClube(),
                cl.getLiga().getLigaId(),
                cl.getLiga().getNomeLiga(),
                cl.getPeriodoEntrada(),
                cl.getPeriodoSaida()
        );
    }
}
