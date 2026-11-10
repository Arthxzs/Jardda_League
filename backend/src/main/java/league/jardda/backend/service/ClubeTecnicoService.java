package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.ClubeTecnicoDTO;
import league.jardda.backend.entity.ClubeTecnico;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.ClubeRepository;
import league.jardda.backend.repository.ClubeTecnicoRepository;
import league.jardda.backend.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubeTecnicoService {

    private final ClubeTecnicoRepository clubeTecnicoRepository;
    private final ClubeRepository clubeRepository;
    private final TecnicoRepository tecnicoRepository;

    public List<ClubeTecnicoDTO> listarPorClube(Long clubeId, boolean apenasAtivos) {
        if (!clubeRepository.existsById(clubeId)) {
            throw new NotFoundException("Clube com id " + clubeId + " não encontrado");
        }
        List<ClubeTecnico> contratos = clubeTecnicoRepository.buscarPorClube(clubeId, apenasAtivos);
        if (contratos.isEmpty()) {
            throw new NotFoundException("Clube com id " + clubeId + " não possui técnicos");
        }
        return contratos.stream().map(this::toDTO).toList();
    }

    public List<ClubeTecnicoDTO> listarPorTecnico(Long tecnicoId, boolean apenasAtivos) {
        if (!tecnicoRepository.existsById(tecnicoId)) {
            throw new NotFoundException("Técnico com id " + tecnicoId + " não encontrado");
        }
        List<ClubeTecnico> contratos = clubeTecnicoRepository.buscarPorTecnico(tecnicoId, apenasAtivos);
        if (contratos.isEmpty()) {
            throw new NotFoundException("Técnico com id " + tecnicoId + " não possui contratos");
        }
        return contratos.stream().map(this::toDTO).toList();
    }

    private ClubeTecnicoDTO toDTO(ClubeTecnico ct) {
        return new ClubeTecnicoDTO(
                ct.getClubeTecnicoId(),
                ct.getClube().getClubeId(),
                ct.getClube().getNomeClube(),
                ct.getTecnico().getTecnicoId(),
                ct.getTecnico().getNomeTecnico(),
                ct.getInicioContratoTecnico(),
                ct.getPrevisaoContratoTecnico(),
                ct.getTerminoContratoTecnico(),
                ct.getValorSalarioTecnico()
        );
    }
}
