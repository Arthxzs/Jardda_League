package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.EstadioDTO;
import league.jardda.backend.dataTransferObj.PartidaDTO;
import league.jardda.backend.service.EstadioService;
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
public class EstadioController {
    private final EstadioService estadioService;
    private final PartidaService partidaService;

    @GetMapping("/estadios")
    public ResponseEntity<List<EstadioDTO>> listEstadio(
            @RequestParam (required = false) String nomeEstadio,
            @RequestParam (required = false) String clubeEstadio,
            @RequestParam (required = false) Long cidadeId,
            @RequestParam (required = false) Long clubeId
    ){
        return ResponseEntity.ok(estadioService.listEstadio(nomeEstadio, clubeEstadio, cidadeId, clubeId));
    }

    @GetMapping("/estadios/{id}")
    public ResponseEntity<EstadioDTO> listEstadioById(@PathVariable long id) {
        return ResponseEntity.ok(estadioService.listEstadioById(id));
    }

    @GetMapping("/estadios/{id}/partidas")
    public ResponseEntity<List<PartidaDTO>> listPartidasDoEstadio(
            @PathVariable long id,
            @RequestParam (required = false) String clubeCasa,
            @RequestParam (required = false) String clubeFora,
            @RequestParam (required = false) Integer rodada,
            @RequestParam (required = false) String liga
    ) {
        return ResponseEntity.ok(partidaService.listPartida(clubeCasa, clubeFora, rodada, liga,  estadioService.listEstadioById(id).nomeEstadio()));
    }
}
