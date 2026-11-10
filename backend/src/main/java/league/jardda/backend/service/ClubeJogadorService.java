package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.ClubeJogadorDTO;
import league.jardda.backend.entity.ClubeJogador;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.ClubeJogadorRepository;
import league.jardda.backend.repository.ClubeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubeJogadorService {

    private final ClubeJogadorRepository clubeJogadorRepository;
    private final ClubeRepository clubeRepository;

    public List<ClubeJogadorDTO> listarElenco(Long clubeId, Boolean apenasAtivos) {
        if (!clubeRepository.existsById(clubeId)) {
            throw new NotFoundException("Clube com id " + clubeId + " não encontrado");
        }

        List<ClubeJogador> contratos = clubeJogadorRepository.buscarPorClube(clubeId, apenasAtivos);

        if (contratos.isEmpty()) {
            throw new NotFoundException("Clube com id " + clubeId + " não possui jogadores");
        }

        return contratos.stream().map(this::toDTO).toList();
    }

    private ClubeJogadorDTO toDTO(ClubeJogador cj) {
        return new ClubeJogadorDTO(
                cj.getClubeJogadorId(),
                cj.getJogador().getJogadorId(),
                cj.getJogador().getNomeJogador(),
                cj.getInicioContratoJogador(),
                cj.getPrevisaoContratoJogador(),
                cj.getTerminoContratoJogador(),
                cj.getValorSalarioJogador()
        );
    }
}
