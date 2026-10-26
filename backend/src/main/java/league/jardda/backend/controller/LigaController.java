package league.jardda.backend.controller;

import league.jardda.backend.entity.Clube;
import league.jardda.backend.entity.Liga;
import league.jardda.backend.service.LigaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LigaController {
    private final LigaService ligaService;

    @GetMapping("/ligas")
    public ResponseEntity<List<Liga>> listAllliga(){
        return ResponseEntity.ok(ligaService.listAllLiga());
    }

    @GetMapping("/ligas/{id}")
    public ResponseEntity<Liga> listLigaById(@PathVariable long id) {
        return ResponseEntity.ok(ligaService.listLigaById(id));
    }
}
