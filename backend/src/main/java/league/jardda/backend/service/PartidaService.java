package league.jardda.backend.service;

import league.jardda.backend.entity.Partida;
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
}
