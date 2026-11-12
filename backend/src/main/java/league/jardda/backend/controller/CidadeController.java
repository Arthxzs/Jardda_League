package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.CidadeDTO;
import league.jardda.backend.dataTransferObj.ClubeDTO;
import league.jardda.backend.dataTransferObj.EstadioDTO;
import league.jardda.backend.service.CidadeService;
import league.jardda.backend.service.ClubeService;
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
    private final ClubeService clubeService;

    @GetMapping("/cidades")
    public ResponseEntity<List<CidadeDTO>> listAllCidade(
            @RequestParam (required = false) String nome,
            @RequestParam (required = false) String pais,
            @RequestParam (required = false) Long paisId
    ){
        return ResponseEntity.ok(cidadeService.listarCidade(nome, pais, paisId));
    }

    @GetMapping("/cidades/{id}")
    public ResponseEntity<CidadeDTO> listCidadeById(@PathVariable long id) {
        return ResponseEntity.ok(cidadeService.buscarPorId(id));
    }

    @GetMapping("/cidades/{id}/estadios")
    public ResponseEntity<List<EstadioDTO>> listEstadiosDaCidade(
            @PathVariable long id,
            @RequestParam (required = false) String nome,
            @RequestParam (required = false) String clube,
            @RequestParam (required = false) Long clubeId
    ) {
        return ResponseEntity.ok(estadioService.listarEstadio(nome, clube, id, clubeId));
    }

    @GetMapping("/cidades/{id}/clubes")
    public ResponseEntity<List<ClubeDTO>> listClubesDaCidade(
            @PathVariable long id,
            @RequestParam(required = false) String nome
    ) {
        return ResponseEntity.ok(clubeService.listarClube(nome, null, id, null));
    }
}
