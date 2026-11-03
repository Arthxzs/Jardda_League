package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.JogadorDTO;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.JogadorRepository;
import league.jardda.backend.utils.StringFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {
    private final JogadorRepository jogadorRepository;

    public List<JogadorDTO> listJogador(String nomeJogador, String paisJogador) {
        if (nomeJogador == null && paisJogador == null) {
            return jogadorRepository.findAll()
                    .stream().map(jogador -> new JogadorDTO(
                            jogador.getJogadorId(),
                            jogador.getNomeJogador(),
                            jogador.getPaisJogador().getNomePais(),
                            jogador.getAniversarioJogador(),
                            jogador.getPosicao(),
                            jogador.getLesionado()
                    )).toList();
        } else if (nomeJogador != null && paisJogador == null) {
            String finalNomeJogador = StringFormatter.toCapitalize(nomeJogador);

            List<JogadorDTO> jogadoresEncontrados = jogadorRepository.findByNomeJogador(finalNomeJogador)
                    .stream().map(jogador -> new JogadorDTO(
                            jogador.getJogadorId(),
                            jogador.getNomeJogador(),
                            jogador.getPaisJogador().getNomePais(),
                            jogador.getAniversarioJogador(),
                            jogador.getPosicao(),
                            jogador.getLesionado()
                    )).toList();

            if (jogadoresEncontrados.isEmpty()) {
                List<JogadorDTO> jogadoresProximosEncontrados = jogadorRepository.findAll()
                        .stream()
                        .filter(jogador -> jogador.getNomeJogador() != null
                                && jogador.getNomeJogador().contains(finalNomeJogador))
                        .map(jogador -> new JogadorDTO(
                        jogador.getJogadorId(),
                        jogador.getNomeJogador(),
                        jogador.getPaisJogador().getNomePais(),
                        jogador.getAniversarioJogador(),
                        jogador.getPosicao(),
                        jogador.getLesionado()
                )).toList();
                if (jogadoresProximosEncontrados.isEmpty()) {
                    throw new NotFoundException("Jogador '" + nomeJogador + "' não encontrado");
                } else {
                    return jogadoresProximosEncontrados;
                }
            } else {
                return jogadoresEncontrados;
            }
        } else {
            String finalPaisJogador = StringFormatter.toCapitalize(paisJogador);

            List<JogadorDTO> jogadoresEncontrados = jogadorRepository.findAll()
                    .stream()
                    .filter(jogador -> jogador.getPaisJogador() != null)
                    .filter(jogador -> jogador.getPaisJogador().getNomePais().equals(finalPaisJogador))
                    .map(jogador -> new JogadorDTO(
                            jogador.getJogadorId(),
                            jogador.getNomeJogador(),
                            jogador.getPaisJogador().getNomePais(),
                            jogador.getAniversarioJogador(),
                            jogador.getPosicao(),
                            jogador.getLesionado()
                    )).toList();

            if (jogadoresEncontrados.isEmpty()) {
                throw new NotFoundException("Nenhum jogador encontrado para '" + paisJogador + "'");
            } else {
                return jogadoresEncontrados;
            }
        }
    }

    public JogadorDTO listJogadorById(long id) {
        try {
            return jogadorRepository.findById(id)
                    .stream()
                    .map(jogador -> new JogadorDTO(
                            jogador.getJogadorId(),
                            jogador.getNomeJogador(),
                            jogador.getPaisJogador().getNomePais(),
                            jogador.getAniversarioJogador(),
                            jogador.getPosicao(),
                            jogador.getLesionado()
                    )).toList().getFirst();
        } catch (RuntimeException e) {
            throw new NotFoundException("Jogador " + id + " não encontrado");
        }
    }
}