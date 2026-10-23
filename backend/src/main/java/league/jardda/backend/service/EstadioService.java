package league.jardda.backend.service;

import league.jardda.backend.entity.Estadio;
import league.jardda.backend.repository.EstadioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadioService {
    private final EstadioRepository estadioRepository;

    public List<Estadio> listAllEstadio() {
        return estadioRepository.findAll();
    }
}
