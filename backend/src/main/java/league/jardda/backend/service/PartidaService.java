package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.PartidaDTO;
import league.jardda.backend.entity.Partida;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.PartidaRepository;
import league.jardda.backend.utils.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidaService {
    private final PartidaRepository partidaRepository;
    private final ParameterValidator parameterValidator;

    private PartidaDTO toDTO(Partida partida) {
        return new PartidaDTO(
                partida.getPartidaId(),
                partida.getClube1().getClubeId(),
                partida.getClube1().getNomeClube(),
                partida.getClube2().getClubeId(),
                partida.getClube2().getNomeClube(),
                partida.getLigaPartida().getLigaId(),
                partida.getLigaPartida().getNomeLiga(),
                partida.getEstadioPartida() == null ? null : partida.getEstadioPartida().getEstadioId(),
                partida.getEstadioPartida() == null ? null : partida.getEstadioPartida().getNomeEstadio(),
                partida.getDataPartida(),
                partida.getPublicoPartida(),
                partida.getRodadaPartida()
        );
    }

    public List<PartidaDTO> listarPartida(String clubeCasa, String clubeFora, Integer rodada,
                                          Long ligaId, String estadio, Long estadioId) {
        parameterValidator.validarLiga(ligaId);
        parameterValidator.validarEstadio(estadioId);

        List<PartidaDTO> partidas = partidaRepository
                .filtrar(clubeCasa, clubeFora, rodada, ligaId, estadio, estadioId, null)
                .stream()
                .map(this::toDTO)
                .toList();

        if (partidas.isEmpty()) {
            throw new NotFoundException("Nenhuma partida encontrada para os filtros informados");
        }
        return partidas;
    }

    public List<PartidaDTO> listarPartidasDoClube(Long clubeId, Integer rodada,
                                                  Long ligaId, String estadio, Long estadioId) {

        parameterValidator.validarClube(clubeId);
        parameterValidator.validarLiga(ligaId);
        parameterValidator.validarEstadio(estadioId);


        List<PartidaDTO> partidas = partidaRepository
                .filtrar(null, null, rodada, ligaId, estadio, estadioId, clubeId)
                .stream()
                .map(this::toDTO)
                .toList();

        if (partidas.isEmpty()) {
            throw new NotFoundException("Clube com id " + clubeId + " não possui partidas");
        }
        return partidas;
    }

    public PartidaDTO buscarPorId(Long id) {
        return partidaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Partida com id " + id + " não encontrada"));
    }
}
