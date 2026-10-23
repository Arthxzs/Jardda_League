package league.jardda.backend.controller;

import league.jardda.backend.entity.Cidade;
import league.jardda.backend.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CidadeController {
    private final CidadeService cidadeService;

    @GetMapping("/cidades")
    public ResponseEntity<List<Cidade>> listAllCidade(){
        return ResponseEntity.ok(cidadeService.listAllCidade());
    }
}
