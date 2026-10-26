package league.jardda.backend.controller;

import league.jardda.backend.entity.Clube;
import league.jardda.backend.entity.Pais;
import league.jardda.backend.service.PaisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaisController {
    private final PaisService paisService;

    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> listAllPais(){
        return ResponseEntity.ok(paisService.listAllPais());
    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<Pais> listPaisById(@PathVariable long id) {
        return ResponseEntity.ok(paisService.listPaisById(id));
    }
}
