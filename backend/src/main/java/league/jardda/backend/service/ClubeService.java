package league.jardda.backend.service;

import league.jardda.backend.entity.Clube;
import league.jardda.backend.repository.ClubeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubeService {
    private final ClubeRepository clubeRepository;

    public List<Clube> listAllClube() {
        return clubeRepository.findAll();
    }
}
