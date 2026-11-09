package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.CidadeDTO;
import league.jardda.backend.dataTransferObj.ClubeDTO;
import league.jardda.backend.dataTransferObj.EstadioDTO;
import league.jardda.backend.service.CidadeService;
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
public class CidadeController {
    private final CidadeService cidadeService;
    private final EstadioService estadioService;

    @GetMapping("/cidades")
    public ResponseEntity<List<CidadeDTO>> listAllCidade(
            @RequestParam (required = false) String nomeCidade,
            @RequestParam (required = false) String paisCidade,
            @RequestParam (required = false) Long paisId
    ){
        return ResponseEntity.ok(cidadeService.listCidade(nomeCidade, paisCidade, paisId));
    }

    @GetMapping("/cidades/{id}")
    public ResponseEntity<CidadeDTO> listCidadeById(@PathVariable long id) {
        return ResponseEntity.ok(cidadeService.listCidadeById(id));
    }

    @GetMapping("/cidades/{id}/estadios")
    public ResponseEntity<List<EstadioDTO>> listEstadiosDaCidade(
            @PathVariable long id,
            @RequestParam (required = false) String nomeEstadio,
            @RequestParam (required = false) String clubeEstadio,
            @RequestParam (required = false) Long clubeId
    ) {
        return ResponseEntity.ok(estadioService.listEstadio(nomeEstadio, clubeEstadio, id, clubeId));
    }
}
