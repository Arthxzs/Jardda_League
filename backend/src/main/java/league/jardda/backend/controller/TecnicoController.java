package league.jardda.backend.controller;

import league.jardda.backend.entity.Tecnico;
import league.jardda.backend.service.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TecnicoController {
    private final TecnicoService tecnicoService;

    @GetMapping("/tecnicos")
    public ResponseEntity<List<Tecnico>> listAllTecnico(){
        return ResponseEntity.ok(tecnicoService.listAllTecnico());
    }
}
