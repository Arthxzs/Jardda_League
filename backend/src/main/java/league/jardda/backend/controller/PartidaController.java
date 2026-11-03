package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.PartidaDTO;
import league.jardda.backend.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PartidaController {
    private final PartidaService partidaService;

    @GetMapping("/partidas")
    public ResponseEntity<List<PartidaDTO>> listPartida(
            @RequestParam (required = false) String clubeCasa,
            @RequestParam (required = false) String clubeFora,
            @RequestParam (required = false) Integer rodada,
            @RequestParam (required = false) String liga
    ){
        return ResponseEntity.ok(partidaService.listPartida(clubeCasa, clubeFora, rodada, liga));
    }

    @GetMapping("/partidas/{id}")
    public ResponseEntity<PartidaDTO> listPartidaById(@PathVariable long id) {
        return ResponseEntity.ok(partidaService.listPartidaById(id));
    }
}
