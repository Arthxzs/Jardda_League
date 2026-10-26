package league.jardda.backend.controller;

import league.jardda.backend.entity.Clube;
import league.jardda.backend.entity.Jogador;
import league.jardda.backend.service.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JogadorController {
    private final JogadorService jogadorService;

    @GetMapping("/jogadores")
    public ResponseEntity<List<Jogador>> listAllJogador(){
        return ResponseEntity.ok(jogadorService.listAllJogador());
    }

    @GetMapping("/jogadores/{id}")
    public ResponseEntity<Jogador> listJogadorById(@PathVariable long id) {
        return ResponseEntity.ok(jogadorService.listJogadorById(id));
    }
}
