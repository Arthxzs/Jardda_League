package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.ClubeJogadorDTO;
import league.jardda.backend.dataTransferObj.JogadorDTO;
import league.jardda.backend.service.ClubeJogadorService;
import league.jardda.backend.service.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JogadorController {
    private final JogadorService jogadorService;
    private final ClubeJogadorService clubeJogadorService;

    @GetMapping("/jogadores")
    public ResponseEntity<List<JogadorDTO>> listJogador(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String pais,
            @RequestParam(required = false) Long paisId
    ) {
        return ResponseEntity.ok(jogadorService.listarJogador(nome, pais, paisId));
    }

    @GetMapping("/jogadores/{id}")
    public ResponseEntity<JogadorDTO> getJogadorById(@PathVariable Long id) {
        return ResponseEntity.ok(jogadorService.buscarPorId(id));
    }

    @GetMapping("/jogadores/{id}/contratos")
    public ResponseEntity<List<ClubeJogadorDTO>> listContratosDeJogador(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean apenasAtivos
    ) {
        return ResponseEntity.ok(clubeJogadorService.listarPorJogador(id, apenasAtivos));
    }

    @GetMapping("/jogadores/{id}/clube-atual")
    public ResponseEntity<ClubeJogadorDTO> getClubeAtual(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(clubeJogadorService.buscarClubeAtual(id));
    }
}
