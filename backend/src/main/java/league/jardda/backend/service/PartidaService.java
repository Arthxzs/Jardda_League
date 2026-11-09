package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.PartidaDTO;
import league.jardda.backend.entity.Partida;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidaService {
    private final PartidaRepository partidaRepository;

    private PartidaDTO toDTO(Partida partida) {
        return new PartidaDTO(
                partida.getPartidaId(),
                partida.getClube1().getNomeClube(),
                partida.getClube2().getNomeClube(),
                partida.getLigaPartida().getNomeLiga(),
                partida.getEstadioPartida().getNomeEstadio(),
                partida.getDataPartida(),
                partida.getPublicoPartida(),
                partida.getRodadaPartida() == null ? 0 : partida.getRodadaPartida()
        );
    }

    public List<PartidaDTO> listPartida(String clubeCasa, String clubeFora, Integer rodada, String liga, String estadioPartida) {
        List<PartidaDTO> partidas = partidaRepository.filtrar(clubeCasa, clubeFora, rodada, liga, estadioPartida)
                .stream()
                .map(this::toDTO)
                .toList();

        if (partidas.isEmpty()) {
            throw new NotFoundException("Nenhuma partida encontrada para os filtros informados");
        }
        return partidas;
    }

    public PartidaDTO listPartidaById(long id) {
        return partidaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Partida " + id  + "não encontrada"));
    }
}
