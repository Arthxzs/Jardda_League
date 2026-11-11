package league.jardda.backend.service;

import league.jardda.backend.dataTransferObj.ClubeDTO;
import league.jardda.backend.entity.Clube;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.ClubeRepository;
import league.jardda.backend.utils.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubeService {
    private final ClubeRepository clubeRepository;
    private final ParameterValidator parameterValidator;

    private ClubeDTO toDTO(Clube clube) {
        return new ClubeDTO(
                clube.getClubeId(),
                clube.getNomeClube(),
                clube.getCidade().getNomeCidade(),
                clube.getCidade().getPais().getNomePais(),
                clube.getCidade().getPais().getSigla()
        );
    }

    public List<ClubeDTO> listarClube(String nome, String pais, Long cidadeId, Long paisId) {
        parameterValidator.validarCidade(cidadeId);
        parameterValidator.validarPais(paisId);

        List<ClubeDTO> clubes = clubeRepository.filtrar(nome, pais, cidadeId, paisId)
                .stream().map(this::toDTO).toList();

        if (clubes.isEmpty()) {
            throw new NotFoundException("Nenhum clube encontrado para os filtros informados");
        }
        return clubes;
    }

    public ClubeDTO buscarPorId(long id) {
        return clubeRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Clube com id " + id + " não encontrado"));
    }
}
