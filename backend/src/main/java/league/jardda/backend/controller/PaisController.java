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
        return ResponseEntity.ok(paisService.listAllPais());
    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<Pais> listPaisById(@PathVariable long id) {
        return ResponseEntity.ok(paisService.listPaisById(id));
    }

    @GetMapping("/paises/{id}/cidades")
    public ResponseEntity<List<CidadeDTO>> listCidadesDoPais(
            @PathVariable long id,
            @RequestParam(required = false) String nomeCidade
    ) {
        return ResponseEntity.ok(cidadeService.listCidade(nomeCidade, null, id));
    }

    @GetMapping("/paises/{id}/ligas")
    public ResponseEntity<List<LigaDTO>> listLigasDoPais(
            @PathVariable long id,
            @RequestParam (required = false) String nomeLiga
    ) {
        return ResponseEntity.ok(ligaService.listLiga(nomeLiga, null, id));
    }

    @GetMapping("/paises/{id}/clubes")
    public ResponseEntity<List<ClubeDTO>> listClubesDaCidade(
            @PathVariable long id,
            @RequestParam(required = false) String nomeClube,
            @RequestParam(required = false) Long clubeId
    ) {
        return ResponseEntity.ok(clubeService.listClube(nomeClube, null, clubeId, id));
    }

    @GetMapping("/paises/{id}/jogadores")
    public ResponseEntity<List<JogadorDTO>> listJogadoresDoPais(
            @PathVariable long id,
            @RequestParam(required = false) String nomeJogador
    ) {
        return ResponseEntity.ok(jogadorService.listJogador(nomeJogador, null, id));
    }

    @GetMapping("/paises/{id}/tecnicos")
    public ResponseEntity<List<TecnicoDTO>> listTecnicosDoPais(
            @PathVariable long id,
            @RequestParam (required = false) String nomeTecnico
    ) {
        return ResponseEntity.ok(tecnicoService.listTecnico(nomeTecnico, null, id));
    }
}
