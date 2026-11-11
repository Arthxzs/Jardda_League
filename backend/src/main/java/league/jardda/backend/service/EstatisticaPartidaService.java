package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.EstatisticaPartidaDTO;
import league.jardda.backend.entity.EstatisticaPartida;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.EstatisticaPartidaRepository;
import league.jardda.backend.utils.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstatisticaPartidaService {

    private final EstatisticaPartidaRepository estatisticaPartidaRepository;
    private final ParameterValidator parameterValidator;

    public EstatisticaPartidaDTO buscarPorPartida(Long partidaId) {
        parameterValidator.validarPartida(partidaId);
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
