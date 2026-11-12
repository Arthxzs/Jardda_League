package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.*;
import league.jardda.backend.entity.Pais;
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
public class PaisController {
    private final PaisService paisService;
    private final CidadeService cidadeService;
    private final LigaService ligaService;
    private final JogadorService jogadorService;
    private final ClubeService clubeService;
    private final TecnicoService tecnicoService;

    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> listAllPais(){
        return ResponseEntity.ok(paisService.listarPais());
    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<Pais> listPaisById(@PathVariable Long id) {
        return ResponseEntity.ok(paisService.buscarPorId(id));
    }

    @GetMapping("/paises/{id}/cidades")
    public ResponseEntity<List<CidadeDTO>> listCidadesDoPais(
            @PathVariable Long id,
            @RequestParam(required = false) String nome
    ) {
        return ResponseEntity.ok(cidadeService.listarCidade(nome, null, id));
    }

    @GetMapping("/paises/{id}/ligas")
    public ResponseEntity<List<LigaDTO>> listLigasDoPais(
            @PathVariable Long id,
            @RequestParam (required = false) String nome
    ) {
        return ResponseEntity.ok(ligaService.listarLiga(nome, null, id));
    }

    @GetMapping("/paises/{id}/clubes")
    public ResponseEntity<List<ClubeDTO>> listClubesDoPais(
            @PathVariable Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Long cidadeId
    ) {
        return ResponseEntity.ok(clubeService.listarClube(nome, null, cidadeId, id));
    }

    @GetMapping("/paises/{id}/jogadores")
    public ResponseEntity<List<JogadorDTO>> listJogadoresDoPais(
            @PathVariable Long id,
            @RequestParam(required = false) String nome
    ) {
        return ResponseEntity.ok(jogadorService.listarJogador(nome, null, id));
    }

    @GetMapping("/paises/{id}/tecnicos")
    public ResponseEntity<List<TecnicoDTO>> listTecnicosDoPais(
            @PathVariable Long id,
            @RequestParam (required = false) String nome
    ) {
        return ResponseEntity.ok(tecnicoService.listarTecnico(nome, null, id));
    }
}
