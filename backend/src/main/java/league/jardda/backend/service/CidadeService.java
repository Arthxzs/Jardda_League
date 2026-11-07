package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.CidadeDTO;
import league.jardda.backend.entity.Cidade;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {
    private final CidadeRepository cidadeRepository;

    private CidadeDTO toDTO(Cidade cidade) {
        return new CidadeDTO(
                cidade.getCidadeId(),
                cidade.getNomeCidade(),
                cidade.getPais().getNomePais(),
                cidade.getPais().getSigla()
        );
    }

    public List<CidadeDTO> listCidade(String nomeCidade, String paisCidade, Long paisId) {
        List<CidadeDTO> cidades = cidadeRepository.filtrar(nomeCidade, paisCidade, paisId)
                .stream().map(this::toDTO).toList();
        if (cidades.isEmpty()) {
            throw new NotFoundException("Nenhuma cidade encontrada para os filtros informados.");
        }
        return cidades;
    }

    public CidadeDTO listCidadeById(long id) {
        return cidadeRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Cidade " + id + " não encontrada"));
    }
}
