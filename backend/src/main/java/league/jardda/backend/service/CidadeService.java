package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.CidadeDTO;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.CidadeRepository;
import league.jardda.backend.utils.StringFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {
    private final CidadeRepository cidadeRepository;

    public List<CidadeDTO> listCidade(String nomeCidade, String paisCidade) {
        if (nomeCidade == null && paisCidade == null){
            return cidadeRepository.findAll()
                    .stream().map(cidade -> new CidadeDTO(
                            cidade.getCidadeId(),
                            cidade.getNomeCidade(),
                            cidade.getPais().getNomePais(),
                            cidade.getPais().getSigla()
                    )).toList();
        } else if (nomeCidade != null && paisCidade == null) {
            String finalNomeCidade = StringFormatter.toCapitalize(nomeCidade);

            List<CidadeDTO> cidadesEncontradas = cidadeRepository.findByNomeCidade(finalNomeCidade)
                    .stream().map(cidade -> new CidadeDTO(
                            cidade.getCidadeId(),
                            cidade.getNomeCidade(),
                            cidade.getPais().getNomePais(),
                            cidade.getPais().getSigla()
                    )).toList();

            if (cidadesEncontradas.isEmpty()) {
                List<CidadeDTO> cidadesProximasEncontradas = cidadeRepository.findAll()
                        .stream()
                        .filter(cidade -> cidade.getNomeCidade() != null
                                && cidade.getNomeCidade().contains(finalNomeCidade))
                        .map(cidade -> new CidadeDTO(
                                cidade.getCidadeId(),
                                cidade.getNomeCidade(),
                                cidade.getPais().getNomePais(),
                                cidade.getPais().getSigla()
                        )).toList();
                if (cidadesProximasEncontradas.isEmpty()) {
                    throw new NotFoundException("Cidade " + nomeCidade + " não encontrada");
                } else {
                    return cidadesProximasEncontradas;
                }
            } else {
                return cidadesEncontradas;
            }
        } else {
            String finalPaisCidade = StringFormatter.toCapitalize(paisCidade);

            List<CidadeDTO> cidadesEncontradas = cidadeRepository.findByPais(finalPaisCidade)
                    .stream().map(cidade -> new CidadeDTO(
                            cidade.getCidadeId(),
                            cidade.getNomeCidade(),
                            cidade.getPais().getNomePais(),
                            cidade.getPais().getSigla()
                    )).toList();
            if (cidadesEncontradas.isEmpty()) {
                throw new NotFoundException("Cidade com país" + paisCidade + " não encontrada");
            } else {
                return cidadesEncontradas;
            }
        }
    }

    public CidadeDTO listCidadeById(long id) {
        try {
            return cidadeRepository.findById(id)
                    .stream().map(cidade -> new CidadeDTO(
                    cidade.getCidadeId(),
                    cidade.getNomeCidade(),
                    cidade.getPais().getNomePais(),
                    cidade.getPais().getSigla()
            )).toList().getFirst();
        } catch (RuntimeException e) {
            throw new NotFoundException("Cidade " + id + " não encontrada");
        }
    }
}
