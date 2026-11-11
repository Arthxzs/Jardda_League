package league.jardda.backend.utils;

import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParameterValidator {
    private final PaisRepository paisRepository;
    private final LigaRepository ligaRepository;
    private final ClubeRepository clubeRepository;
    private final CidadeRepository cidadeRepository;
    private final PartidaRepository partidaRepository;
    private final TecnicoRepository tecnicoRepository;

    public void validarPais(Long paisId) {
        if (paisId != null && !paisRepository.existsById(paisId)) {
            throw new NotFoundException("Pais com id " + paisId + " não encontrado");
        }
    }

    public void validarLiga(Long ligaId) {
        if (ligaId != null && !ligaRepository.existsById(ligaId)) {
            throw new NotFoundException("Liga com id " + ligaId + " não encontrada");
        }
    }

    public void validarClube(Long clubeId) {
        if (clubeId != null && !clubeRepository.existsById(clubeId)) {
            throw new NotFoundException("Clube com id " + clubeId + " não encontrado");
        }
    }

    public void validarCidade(Long cidadeId) {
        if (cidadeId != null && !cidadeRepository.existsById(cidadeId)) {
            throw new NotFoundException("Cidade com id " + cidadeId + " não encontrada");
        }
    }

    public void validarPartida(Long partidaId) {
        if (partidaId != null && !partidaRepository.existsById(partidaId)) {
            throw new NotFoundException("Partida com id " + partidaId + " não encontrada");
        }
    }

    public void validarTecnico(Long tecnicoId) {
        if (tecnicoId != null && !tecnicoRepository.existsById(tecnicoId)) {
            throw new NotFoundException("Técnico com id " + tecnicoId + " não encontrado");
        }
    }
}
