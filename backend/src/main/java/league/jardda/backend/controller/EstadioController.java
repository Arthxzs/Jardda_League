package league.jardda.backend.controller;

import league.jardda.backend.entity.Clube;
import league.jardda.backend.entity.Estadio;
import league.jardda.backend.service.EstadioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EstadioController {
    private final EstadioService estadioService;

    @GetMapping("/estadios")
    public ResponseEntity<List<Estadio>> listAllEstadio(){
        return ResponseEntity.ok(estadioService.listAllEstadio());
    }

    @GetMapping("/estadios/{id}")
    public ResponseEntity<Estadio> listEstadioById(@PathVariable long id) {
        return ResponseEntity.ok(estadioService.listEstadioById(id));
    }
}
