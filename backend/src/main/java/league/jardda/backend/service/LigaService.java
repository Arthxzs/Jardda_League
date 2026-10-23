package league.jardda.backend.service;

import league.jardda.backend.entity.Liga;
import league.jardda.backend.repository.LigaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LigaService {
    private final LigaRepository ligaRepository;

    public List<Liga> listAllLiga() {
        return ligaRepository.findAll();
    }
}
