package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.ClubeDTO;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.ClubeRepository;
import league.jardda.backend.utils.StringFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubeService {
    private final ClubeRepository clubeRepository;

    public List<ClubeDTO> listClube(String nomeClube, String paisClube) {
        if (nomeClube == null && paisClube == null){
            return clubeRepository.findAll()
                    .stream().map(clube -> new ClubeDTO(
                            clube.getClubeId(),
                            clube.getNomeClube(),
                            clube.getCidade().getNomeCidade(),
                            clube.getCidade().getPais().getNomePais(),
                            clube.getCidade().getPais().getSigla()
                    )).toList();
        } else if (nomeClube != null && paisClube == null) {
            String finalNomeClube = StringFormatter.toCapitalize(nomeClube);

            List<ClubeDTO> clubesEncontrados = clubeRepository.findByNomeClube(finalNomeClube)
                    .stream().map(clube -> new ClubeDTO(
                            clube.getClubeId(),
                            clube.getNomeClube(),
                            clube.getCidade().getNomeCidade(),
                            clube.getCidade().getPais().getNomePais(),
                            clube.getCidade().getPais().getSigla()
                    )).toList();
            if (clubesEncontrados.isEmpty()) {
                List<ClubeDTO> clubesProximosEncontrados = clubeRepository.findAll()
                        .stream()
                        .filter(clube -> clube.getNomeClube() != null
                                && clube.getNomeClube().contains(finalNomeClube))
                        .map(clube -> new ClubeDTO(
                                clube.getClubeId(),
                                clube.getNomeClube(),
                                clube.getCidade().getNomeCidade(),
                                clube.getCidade().getPais().getNomePais(),
                                clube.getCidade().getPais().getSigla()
                        )).toList();
                if (clubesProximosEncontrados.isEmpty()) {
                    throw new NotFoundException("Clube '" + nomeClube + "' não encontrado");
                } else {
                    return clubesProximosEncontrados;
                }
            } else {
                return clubesEncontrados;
            }
        } else {
            String finalPaisClube = StringFormatter.toCapitalize(paisClube);

            List<ClubeDTO> clubesEncontrados = clubeRepository.findAll()
                    .stream().filter(clube -> clube.getCidade().getPais().getNomePais().equals(finalPaisClube))
                    .map(clube -> new ClubeDTO(
                            clube.getClubeId(),
                            clube.getNomeClube(),
                            clube.getCidade().getNomeCidade(),
                            clube.getCidade().getPais().getNomePais(),
                            clube.getCidade().getPais().getSigla()
                    )).toList();
            if (clubesEncontrados.isEmpty()) {
                throw new NotFoundException("Clube com país '" + finalPaisClube + "' não encontrado");
            } else {
                return clubesEncontrados;
            }
        }
    }

    public ClubeDTO listClubeById(long id) {
        try {
            return clubeRepository.findById(id)
                    .stream().map(clube -> new ClubeDTO(
                            clube.getClubeId(),
                            clube.getNomeClube(),
                            clube.getCidade().getNomeCidade(),
                            clube.getCidade().getPais().getNomePais(),
                            clube.getCidade().getPais().getSigla()
                    )).toList().getFirst();
        } catch (RuntimeException e) {
            throw new NotFoundException("Clube " + id + " não encontrado");
        }
    }
}
