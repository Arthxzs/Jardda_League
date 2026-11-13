package league.jardda.backend.dataTransferObj;

import java.time.LocalDate;

public record JogadorDTO(
        Long id,
        String nome,
        String pais,
        LocalDate dataDeNascimento,
        String posicao,
        Boolean lesionado
) {
}
