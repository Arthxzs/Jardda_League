package league.jardda.backend.service;

import league.jardda.backend.entity.Pais;
import league.jardda.backend.repository.PaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaisService {
    private final PaisRepository paisRepository;

    public List<Pais> listAllPais() {
        return paisRepository.findAll();
    }
}
