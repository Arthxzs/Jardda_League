package league.jardda.backend.controller;

import league.jardda.backend.entity.Clube;
import league.jardda.backend.entity.Partida;
import league.jardda.backend.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PartidaController {
    private final PartidaService partidaService;

    @GetMapping("/partidas")
    public ResponseEntity<List<Partida>> listAllPartida(){
        return ResponseEntity.ok(partidaService.listAllPartida());
    }

    @GetMapping("/partidas/{id}")
    public ResponseEntity<Partida> listPartidaById(@PathVariable long id) {
        return ResponseEntity.ok(partidaService.listPartidaById(id));
    }
}
