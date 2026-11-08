package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.LigaDTO;
import league.jardda.backend.dataTransferObj.PartidaDTO;
import league.jardda.backend.service.LigaService;
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
public class LigaController {
    private final LigaService ligaService;
    private final PartidaService partidaService;

    @GetMapping("/ligas")
    public ResponseEntity<List<LigaDTO>> listLiga(
            @RequestParam (required = false) String nomeLiga,
            @RequestParam (required = false) String paisLiga,
            @RequestParam (required = false) Long paisId
    ){
        return ResponseEntity.ok(ligaService.listLiga(nomeLiga, paisLiga, paisId));
    }

    @GetMapping("/ligas/{id}")
    public ResponseEntity<LigaDTO> listLigaById(@PathVariable long id) {
        return ResponseEntity.ok(ligaService.listLigaById(id));
    }

    @GetMapping("/ligas/{id}/partidas")
    public ResponseEntity<List<PartidaDTO>> listPartidasDaLiga(
            @PathVariable long id,
            @RequestParam (required = false) String clubeCasa,
            @RequestParam (required = false) String clubeFora,
            @RequestParam (required = false) Integer rodada
    ) {
        return ResponseEntity.ok(partidaService.listPartida(clubeCasa, clubeFora, rodada, ligaService.listLigaById(id).nomeLiga()));
    }
}
