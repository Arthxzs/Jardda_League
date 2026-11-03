package league.jardda.backend.controller;

import league.jardda.backend.dataTransferObj.CidadeDTO;
import league.jardda.backend.service.CidadeService;
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

    @GetMapping("/cidades")
    public ResponseEntity<List<CidadeDTO>> listAllCidade(
            @RequestParam (required = false) String nomeCidade,
            @RequestParam (required = false) String paisCidade
    ){
        return ResponseEntity.ok(cidadeService.listCidade(nomeCidade, paisCidade));
    }

    @GetMapping("/cidades/{id}")
    public ResponseEntity<CidadeDTO> listCidadeById(@PathVariable long id) {
        return ResponseEntity.ok(cidadeService.listCidadeById(id));
    }
}
