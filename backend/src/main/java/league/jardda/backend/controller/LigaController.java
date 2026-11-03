package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.LigaDTO;
import league.jardda.backend.service.LigaService;
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

    @GetMapping("/ligas")
    public ResponseEntity<List<LigaDTO>> listLiga(
            @RequestParam (required = false) String nomeLiga,
            @RequestParam (required = false) String paisLiga
    ){
        return ResponseEntity.ok(ligaService.listLiga(nomeLiga, paisLiga));
    }

    @GetMapping("/ligas/{id}")
    public ResponseEntity<LigaDTO> listLigaById(@PathVariable long id) {
        return ResponseEntity.ok(ligaService.listLigaById(id));
    }
}
