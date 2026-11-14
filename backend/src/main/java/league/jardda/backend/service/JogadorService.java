package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.JogadorDTO;
import league.jardda.backend.entity.Jogador;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.JogadorRepository;
import league.jardda.backend.utils.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {
    private final JogadorRepository jogadorRepository;
    private final ParameterValidator parameterValidator;

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

    public List<JogadorDTO> listarJogador(String nome, String pais, Long paisId) {
        parameterValidator.validarPais(paisId);

        List<JogadorDTO> jogadores = jogadorRepository.filtrar(nome, pais, paisId)
                .stream().map(this::toDTO).toList();

        if (jogadores.isEmpty()) {
            throw new NotFoundException("Nenhum jogador encontrado para os filtros informados.");
        }
        return jogadores;
    }

    public JogadorDTO buscarPorId(Long id) {
        return jogadorRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Jogador com id " + id + " não encontrado."));
    }
}