package league.jardda.backend.service;

import league.jardda.backend.entity.Pais;
import league.jardda.backend.exception.NotFoundException;
import league.jardda.backend.repository.PaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaisService {
    private final PaisRepository paisRepository;

    public List<Pais> listarPais() {
        List<Pais> paises = paisRepository.findAll();

        if (paises.isEmpty()) {
            throw new NotFoundException("Nenhum país encontrado.");
        }

        return paises;
    }

    public Pais buscarPorId(Long id) {
        return paisRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("País com id " + id + " não encontrado"));
    }
}
