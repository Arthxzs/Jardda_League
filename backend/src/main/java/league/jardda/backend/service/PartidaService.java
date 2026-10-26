package league.jardda.backend.service;

import league.jardda.backend.entity.Partida;
import league.jardda.backend.entity.Tecnico;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidaService {
    private final PartidaRepository partidaRepository;

    public List<Partida> listAllPartida() {
        return partidaRepository.findAll();
    }

    public Partida listPartidaById(long id) {
        return partidaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Partida " + id + " não encontrada"));
    }
}
