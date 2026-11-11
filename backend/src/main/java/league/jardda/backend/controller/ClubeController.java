package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.*;
import league.jardda.backend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClubeController {
    private final ClubeService clubeService;
    private final EstadioService estadioService;
    private final PartidaService partidaService;
    private final ClubeJogadorService clubeJogadorService;
    private final ClubeTecnicoService clubeTecnicoService;
    private final ClubeLigaService clubeLigaService;

    @GetMapping("/clubes")
    public ResponseEntity<List<ClubeDTO>> listClubes(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String pais,
            @RequestParam(required = false) Long cidadeId,
            @RequestParam(required = false) Long paisId
    ){
        return ResponseEntity.ok(clubeService.listarClube(nome, pais, cidadeId, paisId));
    }

    @GetMapping("/clubes/{id}")
    public ResponseEntity<ClubeDTO> listClubeById(@PathVariable long id) {
        return ResponseEntity.ok(clubeService.buscarPorId(id));
    }

    @GetMapping("/clubes/{id}/estadios")
    public ResponseEntity<List<EstadioDTO>> listEstadiosDoClube(
            @PathVariable long id,
            @RequestParam (required = false) String nome
    ) {
        return ResponseEntity.ok(estadioService.listarEstadio(nome, null, null, id));
    }

    @GetMapping("/clubes/{id}/partidas")
    public ResponseEntity<List<PartidaDTO>> listPartidasDoClube(
            @PathVariable long id,
            @RequestParam(required = false) Integer rodada,
            @RequestParam(required = false) Long ligaId,
            @RequestParam(required = false) String estadio
    ) {
        return ResponseEntity.ok(partidaService.listarPartidasDoClube(id, rodada, ligaId, estadio));
    }

    @GetMapping("/clubes/{id}/elenco")
    public ResponseEntity<List<ClubeJogadorDTO>> elenco(
            @PathVariable Long id,
            @RequestParam (defaultValue = "false") boolean apenasAtivos
    ) {
        return ResponseEntity.ok(clubeJogadorService.listarElenco(id, apenasAtivos));
    }

    @GetMapping("/clubes/{id}/tecnicos")
    public ResponseEntity<List<ClubeTecnicoDTO>> tecnicosDoClube(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean apenasAtivos) {
        return ResponseEntity.ok(clubeTecnicoService.listarPorClube(id, apenasAtivos));
    }

    @GetMapping("/clubes/{id}/ligas")
    public ResponseEntity<List<ClubeLigaDTO>> ligasDoClube(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean apenasAtivos) {
        return ResponseEntity.ok(clubeLigaService.listarPorClube(id, apenasAtivos));
    }
}
