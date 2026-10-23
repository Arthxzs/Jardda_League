package league.jardda.backend.controller;

import league.jardda.backend.entity.Clube;
import league.jardda.backend.service.ClubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClubeController {
    private final ClubeService service;

    @GetMapping("/clubes")
    public ResponseEntity<List<Clube>> listAllClubes(){
        return ResponseEntity.ok(service.listAllClube());
    }
}
