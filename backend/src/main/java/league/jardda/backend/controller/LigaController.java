package league.jardda.backend.controller;

import league.jardda.backend.entity.Liga;
import league.jardda.backend.service.LigaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
