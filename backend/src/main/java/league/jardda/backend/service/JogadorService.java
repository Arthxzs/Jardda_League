package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.JogadorDTO;
import league.jardda.backend.entity.Jogador;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {
    private final JogadorRepository jogadorRepository;

    private JogadorDTO toDTO(Jogador jogador) {
        return new JogadorDTO(
                jogador.getJogadorId(),
                jogador.getNomeJogador(),
                jogador.getPaisJogador().getNomePais(),
                jogador.getAniversarioJogador(),
                jogador.getPosicao(),
                jogador.getLesionado()
        );
    }

    public List<JogadorDTO> listJogador(String nomeJogador, String paisJogador, Long paisId) {
        List<JogadorDTO> jogadores = jogadorRepository.filtrar(nomeJogador, paisJogador, paisId)
                .stream().map(this::toDTO).toList();
        if (jogadores.isEmpty()) {
            throw new NotFoundException("Nenhum jogador encontrado para os filtros informados");
        }
        return jogadores;
    }

    public JogadorDTO listJogadorById(long id) {
        return jogadorRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Jogador " + id + " não encontrado"));
    }
}