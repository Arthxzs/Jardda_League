package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.EstatisticaPartidaDTO;
import league.jardda.backend.dataTransferObj.PartidaDTO;
import league.jardda.backend.service.EstatisticaPartidaService;
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
    private final EstatisticaPartidaService estatisticaPartidaService;

    @GetMapping("/partidas")
    public ResponseEntity<List<PartidaDTO>> listPartida(
            @RequestParam(required = false) String clubeCasa,
            @RequestParam(required = false) String clubeFora,
            @RequestParam(required = false) Integer rodada,
            @RequestParam(required = false) Long ligaId,
            @RequestParam(required = false) String estadio,
            @RequestParam(required = false) Long estadioId
    ) {
        return ResponseEntity.ok(partidaService.listarPartida(clubeCasa, clubeFora, rodada, ligaId, estadio, estadioId));
    }

    @GetMapping("/partidas/{id}")
    public ResponseEntity<PartidaDTO> listPartidaById(@PathVariable Long id) {
        return ResponseEntity.ok(partidaService.buscarPorId(id));
    }

    @GetMapping("/partidas/{id}/estatisticas")
    public ResponseEntity<EstatisticaPartidaDTO> estatisticas(@PathVariable Long id) {
        return ResponseEntity.ok(estatisticaPartidaService.buscarPorPartida(id));
    }
}
