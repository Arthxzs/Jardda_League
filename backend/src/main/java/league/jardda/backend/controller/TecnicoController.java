package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.ClubeTecnicoDTO;
import league.jardda.backend.dataTransferObj.TecnicoDTO;
import league.jardda.backend.service.ClubeTecnicoService;
import league.jardda.backend.service.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TecnicoController {
    private final TecnicoService tecnicoService;
    private final ClubeTecnicoService clubeTecnicoService;

    @GetMapping("/tecnicos")
    public ResponseEntity<List<TecnicoDTO>> listTecnico(
            @RequestParam (required = false) String nomeTecnico,
            @RequestParam (required = false) String paisTecnico,
            @RequestParam (required = false) Long paisId
    ){
        return ResponseEntity.ok(tecnicoService.listTecnico(nomeTecnico, paisTecnico, paisId));
    }

    @GetMapping("/tecnicos/{id}")
    public ResponseEntity<TecnicoDTO> listTecnicoById(@PathVariable long id) {
        return ResponseEntity.ok(tecnicoService.listTecnicoById(id));
    }

    @GetMapping("/tecnicos/{id}/contratos")
    public ResponseEntity<List<ClubeTecnicoDTO>> contratosDoTecnico(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean apenasContratosAtivos) {
        return ResponseEntity.ok(clubeTecnicoService.listarPorTecnico(id, apenasContratosAtivos));
    }
}
