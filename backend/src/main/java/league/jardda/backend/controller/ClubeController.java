package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.ClubeDTO;
import league.jardda.backend.service.ClubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClubeController {
    private final ClubeService clubeService;

    @GetMapping("/clubes")
    public ResponseEntity<List<ClubeDTO>> listClubes(
            @RequestParam(required = false) String nomeClube,
            @RequestParam(required = false) String paisClube,
            @RequestParam(required = false) Long clubeId,
            @RequestParam(required = false) Long paisId
    ){
        return ResponseEntity.ok(clubeService.listClube(nomeClube, paisClube, clubeId, paisId));
    }

    @GetMapping("/clubes/{id}")
    public ResponseEntity<ClubeDTO> listClubeById(@PathVariable long id) {
        return ResponseEntity.ok(clubeService.listClubeById(id));
    }
}
