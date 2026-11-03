package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.JogadorDTO;
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

    @GetMapping("/jogadores")
    public ResponseEntity<List<JogadorDTO>> listAllJogador(
            @RequestParam(required = false) String nomeJogador,
            @RequestParam (required = false) String paisJogador
    ){
        return ResponseEntity.ok(jogadorService.listJogador(nomeJogador, paisJogador));
    }

    @GetMapping("/jogadores/{id}")
    public ResponseEntity<JogadorDTO> listJogadorById(@PathVariable long id) {
        return ResponseEntity.ok(jogadorService.listJogadorById(id));
    }
}
