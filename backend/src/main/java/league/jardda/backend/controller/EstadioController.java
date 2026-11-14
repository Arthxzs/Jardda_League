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
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String clube,
            @RequestParam(required = false) Long cidadeId,
            @RequestParam(required = false) Long clubeId
    ) {
        return ResponseEntity.ok(estadioService.listarEstadio(nome, clube, cidadeId, clubeId));
    }

    @GetMapping("/estadios/{id}")
    public ResponseEntity<EstadioDTO> getEstadioById(@PathVariable Long id) {
        return ResponseEntity.ok(estadioService.buscarPorId(id));
    }

    @GetMapping("/estadios/{id}/partidas")
    public ResponseEntity<List<PartidaDTO>> listPartidasDoEstadio(
            @PathVariable Long id,
            @RequestParam(required = false) String clubeCasa,
            @RequestParam(required = false) String clubeFora,
            @RequestParam(required = false) Integer rodada,
            @RequestParam(required = false) Long ligaId
    ) {
        return ResponseEntity.ok(partidaService.listarPartida(clubeCasa, clubeFora, rodada, ligaId, null, id));
    }
}
