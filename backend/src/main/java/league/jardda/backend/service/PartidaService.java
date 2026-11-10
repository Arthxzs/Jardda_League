package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.PartidaDTO;
import league.jardda.backend.entity.Partida;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.ClubeRepository;
import league.jardda.backend.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidaService {
    private final PartidaRepository partidaRepository;
    private final ClubeRepository clubeRepository;

    private PartidaDTO toDTO(Partida partida) {
        return new PartidaDTO(
                partida.getPartidaId(),
                partida.getClube1().getNomeClube(),
                partida.getClube2().getNomeClube(),
                partida.getLigaPartida().getNomeLiga(),
                partida.getEstadioPartida() == null ? null : partida.getEstadioPartida().getNomeEstadio(),
                partida.getDataPartida(),
                partida.getPublicoPartida(),
                partida.getRodadaPartida() == null ? 0 : partida.getRodadaPartida()
        );
    }

    public List<PartidaDTO> listPartida(String clubeCasa, String clubeFora, Integer rodada,
                                        String liga, String estadioPartida) {
        List<PartidaDTO> partidas = partidaRepository
                .filtrar(clubeCasa, clubeFora, rodada, liga, estadioPartida, null)
                .stream()
                .map(this::toDTO)
                .toList();

        if (partidas.isEmpty()) {
            throw new NotFoundException("Nenhuma partida encontrada para os filtros informados");
        }
        return partidas;
    }

    public List<PartidaDTO> listPartidasDoClube(Long clubeId, Integer rodada,
                                                String liga, String estadioPartida) {
        if (!clubeRepository.existsById(clubeId)) {
            throw new NotFoundException("Clube com id " + clubeId + " não encontrado");
        }

        List<PartidaDTO> partidas = partidaRepository
                .filtrar(null, null, rodada, liga, estadioPartida, clubeId)
                .stream()
                .map(this::toDTO)
                .toList();

        if (partidas.isEmpty()) {
            throw new NotFoundException("Clube com id " + clubeId + " não possui partidas");
        }
        return partidas;
    }

    public PartidaDTO listPartidaById(long id) {
        return partidaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Partida " + id  + " não encontrada"));
    }
}
