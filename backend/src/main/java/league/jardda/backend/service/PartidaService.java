package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.PartidaDTO;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.PartidaRepository;
import league.jardda.backend.utils.StringFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidaService {
    private final PartidaRepository partidaRepository;

    public List<PartidaDTO> listPartida(String clubeCasa, String clubeFora, Integer rodada, String liga) {
        if (
                clubeCasa == null
                && clubeFora == null
                && rodada == null
                && liga == null
        ) {
            return partidaRepository.findAll()
                    .stream().map(partida -> new PartidaDTO(
                            partida.getPartidaId(),
                            partida.getClube1().getNomeClube(),
                            partida.getClube2().getNomeClube(),
                            partida.getLigaPartida().getNomeLiga(),
                            partida.getEstadioPartida().getNomeEstadio(),
                            partida.getDataPartida(),
                            partida.getPublicoPartida(),
                            partida.getRodadaPartida() == null ? 0 : partida.getRodadaPartida()
                    )).toList();
        } else if (
                clubeCasa != null
                && clubeFora == null
                && rodada == null
                && liga == null) {

            String finalClubeCasa = StringFormatter.toCapitalize(clubeCasa);

            List<PartidaDTO> partidasEncontradas = partidaRepository.findAll()
                    .stream()
                    .filter(partida -> partida.getClube1().getNomeClube().equals(finalClubeCasa))
                    .map(partida -> new PartidaDTO(
                            partida.getPartidaId(),
                            partida.getClube1().getNomeClube(),
                            partida.getClube2().getNomeClube(),
                            partida.getLigaPartida().getNomeLiga(),
                            partida.getEstadioPartida().getNomeEstadio(),
                            partida.getDataPartida(),
                            partida.getPublicoPartida(),
                            partida.getRodadaPartida() == null ? 0 : partida.getRodadaPartida()
                    )).toList();

            if (partidasEncontradas.isEmpty()) {
                throw new NotFoundException("Partida com clubeCasa '" + clubeCasa + "' não encontrada");
            } else {
                return partidasEncontradas;
            }
        } else if (
                clubeCasa == null
                        && clubeFora != null
                        && rodada == null
                        && liga == null) {

            String finalClubeFora = StringFormatter.toCapitalize(clubeFora);

            List<PartidaDTO> partidasEncontradas = partidaRepository.findAll()
                    .stream()
                    .filter(partida -> partida.getClube1().getNomeClube().equals(finalClubeFora))
                    .map(partida -> new PartidaDTO(
                            partida.getPartidaId(),
                            partida.getClube1().getNomeClube(),
                            partida.getClube2().getNomeClube(),
                            partida.getLigaPartida().getNomeLiga(),
                            partida.getEstadioPartida().getNomeEstadio(),
                            partida.getDataPartida(),
                            partida.getPublicoPartida(),
                            partida.getRodadaPartida() == null ? 0 : partida.getRodadaPartida()
                    )).toList();

            if (partidasEncontradas.isEmpty()) {
                throw new NotFoundException("Partida com clubeFora '" + clubeFora + "' não encontrada");
            } else {
                return partidasEncontradas;
            }
        } else if (
                clubeCasa != null
                        && clubeFora != null
                        && rodada == null
                        && liga == null) {

            String finalClubeCasa = StringFormatter.toCapitalize(clubeCasa);
            String finalClubeFora = StringFormatter.toCapitalize(clubeFora);

            List<PartidaDTO> partidasEncontradas = partidaRepository.findAll()
                    .stream()
                    .filter(partida -> partida.getClube1().getNomeClube().equals(finalClubeCasa)
                            && partida.getClube2().getNomeClube().equals(finalClubeFora))
                    .map(partida -> new PartidaDTO(
                            partida.getPartidaId(),
                            partida.getClube1().getNomeClube(),
                            partida.getClube2().getNomeClube(),
                            partida.getLigaPartida().getNomeLiga(),
                            partida.getEstadioPartida().getNomeEstadio(),
                            partida.getDataPartida(),
                            partida.getPublicoPartida(),
                            partida.getRodadaPartida() == null ? 0 : partida.getRodadaPartida()
                    )).toList();

            if (partidasEncontradas.isEmpty()) {
                throw new NotFoundException("Partida entre clubes '" + clubeCasa + "' e '" + clubeFora + "' não encontrada");
            } else {
                return partidasEncontradas;
            }
        } else if (
                clubeCasa == null
                        && clubeFora == null
                        && rodada != null
                        && liga == null) {

            List<PartidaDTO> partidasEncontradas = partidaRepository.findAll()
                    .stream()
                    .filter(partida -> partida.getRodadaPartida() != null
                            && partida.getRodadaPartida().equals(rodada))
                    .map(partida -> new PartidaDTO(
                            partida.getPartidaId(),
                            partida.getClube1().getNomeClube(),
                            partida.getClube2().getNomeClube(),
                            partida.getLigaPartida().getNomeLiga(),
                            partida.getEstadioPartida().getNomeEstadio(),
                            partida.getDataPartida(),
                            partida.getPublicoPartida(),
                            partida.getRodadaPartida()
                    )).toList();

            if (partidasEncontradas.isEmpty()) {
                throw new NotFoundException("Partida com rodada '" + rodada + "' não encontrada");
            } else {
                return partidasEncontradas;
            }
        } else {
            String finalLigaPartida = StringFormatter.toCapitalize(liga);

            List<PartidaDTO> partidasEncontradas = partidaRepository.findAll()
                    .stream()
                    .filter(partida -> partida.getLigaPartida().getNomeLiga().contains(finalLigaPartida))
                    .map(partida -> new PartidaDTO(
                            partida.getPartidaId(),
                            partida.getClube1().getNomeClube(),
                            partida.getClube2().getNomeClube(),
                            partida.getLigaPartida().getNomeLiga(),
                            partida.getEstadioPartida().getNomeEstadio(),
                            partida.getDataPartida(),
                            partida.getPublicoPartida(),
                            partida.getRodadaPartida() == null ? 0 : partida.getRodadaPartida()
                    )).toList();

            if (partidasEncontradas.isEmpty()) {
                throw new NotFoundException("Partida com liga '" + liga + "' não encontrada");
            } else {
                return partidasEncontradas;
            }
        }
    }

    public PartidaDTO listPartidaById(long id) {
        try {
            return partidaRepository.findById(id)
                    .stream().map(partida -> new PartidaDTO(
                            partida.getPartidaId(),
                            partida.getClube1().getNomeClube(),
                            partida.getClube2().getNomeClube(),
                            partida.getLigaPartida().getNomeLiga(),
                            partida.getEstadioPartida().getNomeEstadio(),
                            partida.getDataPartida(),
                            partida.getPublicoPartida(),
                            partida.getRodadaPartida() == null ? 0 : partida.getRodadaPartida()
                    )).toList().getFirst();
        } catch (RuntimeException e) {
            throw new NotFoundException("Partida " + id + " não encontrada");
        }
    }
}
