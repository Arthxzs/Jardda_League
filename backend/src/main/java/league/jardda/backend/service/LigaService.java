package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.LigaDTO;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.LigaRepository;
import league.jardda.backend.utils.StringFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LigaService {
    private final LigaRepository ligaRepository;

    public List<LigaDTO> listLiga(String nomeLiga, String paisLiga) {
        if (nomeLiga == null && paisLiga == null) {
            return ligaRepository.findAll()
                    .stream().map(liga -> new LigaDTO(
                            liga.getLigaId(),
                            liga.getNomeLiga(),
                            liga.getPaisLiga() == null ? "indefinido" : liga.getPaisLiga().getNomePais(),
                            liga.getCategoriaLiga()
                    )).toList();
        } else if (nomeLiga != null && paisLiga == null) {
            String finalNomeLiga = StringFormatter.toCapitalize(nomeLiga);
            List<LigaDTO> ligasEncontradas = ligaRepository.findByNomeLiga(finalNomeLiga)
                    .stream().map(liga -> new LigaDTO(
                            liga.getLigaId(),
                            liga.getNomeLiga(),
                            liga.getPaisLiga() == null ? "indefinido" : liga.getPaisLiga().getNomePais(),
                            liga.getCategoriaLiga()
                    )).toList();
            if (ligasEncontradas.isEmpty()) {
                List<LigaDTO> ligasProximasEncontradas = ligaRepository.findAll()
                        .stream()
                        .filter(liga -> liga.getNomeLiga() != null
                                && liga.getNomeLiga().contains(finalNomeLiga))
                        .map(liga -> new LigaDTO(
                                liga.getLigaId(),
                                liga.getNomeLiga(),
                                liga.getPaisLiga() == null ? "indefinido" : liga.getPaisLiga().getNomePais(),
                                liga.getCategoriaLiga()
                        )).toList();
                if (ligasProximasEncontradas.isEmpty()) {
                    throw new NotFoundException("Liga '" + nomeLiga + "' não encontrada");
                } else {
                    return ligasProximasEncontradas;
                }
            } else {
                return ligasEncontradas;
            }
        } else {
            String finalPaisLiga = StringFormatter.toCapitalize(paisLiga);
            List<LigaDTO> ligasEncontradas = ligaRepository.findAll()
                    .stream()
                    .filter(liga -> liga.getPaisLiga() != null
                            && liga.getPaisLiga().getNomePais().equals(finalPaisLiga))
                    .map(liga -> new LigaDTO(
                            liga.getLigaId(),
                            liga.getNomeLiga(),
                            liga.getPaisLiga().getNomePais(),
                            liga.getCategoriaLiga()
                    )).toList();
            if (ligasEncontradas.isEmpty()) {
                throw new NotFoundException("Liga com país '" + paisLiga + "' não encontrada");
            } else {
                return ligasEncontradas;
            }
        }
    }

    public LigaDTO listLigaById(long id) {
        try {
            return ligaRepository.findById(id)
                    .stream().map(liga -> new LigaDTO(
                            liga.getLigaId(),
                            liga.getNomeLiga(),
                            liga.getPaisLiga() == null ? "indefinido" : liga.getPaisLiga().getNomePais(),
                            liga.getCategoriaLiga()
                    )).toList().getFirst();
        } catch (RuntimeException e) {
            throw new NotFoundException("Liga " + id + " não encontrada");
        }
    }
}
