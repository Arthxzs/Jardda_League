package league.jardda.backend.service;

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

    public List<Jogador> listAllJogador() {
        return jogadorRepository.findAll();
    }

    public Jogador listJogadorById(long id) {
        return jogadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Jogador " + id + " não encontrado"));
    }
}