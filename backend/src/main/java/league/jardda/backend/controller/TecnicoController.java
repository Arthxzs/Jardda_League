package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.TecnicoDTO;
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

    @GetMapping("/tecnicos")
    public ResponseEntity<List<TecnicoDTO>> listTecnico(
            @RequestParam (required = false) String nomeTecnico,
            @RequestParam (required = false) String paisTecnico
    ){
        return ResponseEntity.ok(tecnicoService.listTecnico(nomeTecnico, paisTecnico));
    }

    @GetMapping("/tecnicos/{id}")
    public ResponseEntity<TecnicoDTO> listTecnicoById(@PathVariable long id) {
        return ResponseEntity.ok(tecnicoService.listTecnicoById(id));
    }
}
