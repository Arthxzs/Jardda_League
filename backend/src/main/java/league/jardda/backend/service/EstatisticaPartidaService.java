package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.EstatisticaPartidaDTO;
import league.jardda.backend.entity.EstatisticaPartida;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.EstatisticaPartidaRepository;
import league.jardda.backend.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstatisticaPartidaService {

    private final EstatisticaPartidaRepository estatisticaPartidaRepository;
    private final PartidaRepository partidaRepository;

    public EstatisticaPartidaDTO buscarPorPartida(Long partidaId) {
        if (!partidaRepository.existsById(partidaId)) {
            throw new NotFoundException("Partida com id " + partidaId + " não encontrada");
        }
        return estatisticaPartidaRepository.findById(partidaId)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException(
                        "Partida com id " + partidaId + " não possui estatísticas"));
    }

    private EstatisticaPartidaDTO toDTO(EstatisticaPartida e) {
        return new EstatisticaPartidaDTO(
                e.getPartidaId(),
                e.getTime1Gols(), e.getTime2Gols(),
                e.getTime1Cartoes(), e.getTime2Cartoes(),
                e.getTime1Chutes(), e.getTime2Chutes()
        );
    }
}
