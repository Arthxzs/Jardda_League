package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.EstadioDTO;
import league.jardda.backend.service.EstadioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EstadioController {
    private final EstadioService estadioService;

    @GetMapping("/estadios")
    public ResponseEntity<List<EstadioDTO>> listEstadio(
            @RequestParam (required = false) String nomeEstadio,
            @RequestParam (required = false) String clubeEstadio
    ){
        return ResponseEntity.ok(estadioService.listEstadio(nomeEstadio, clubeEstadio));
    }

    @GetMapping("/estadios/{id}")
    public ResponseEntity<EstadioDTO> listEstadioById(@PathVariable long id) {
        return ResponseEntity.ok(estadioService.listEstadioById(id));
    }
}
