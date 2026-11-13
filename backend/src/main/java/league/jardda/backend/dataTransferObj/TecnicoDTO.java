package league.jardda.backend.dataTransferObj;

import java.time.LocalDate;

public record TecnicoDTO(
        Long id,
        String nome,
        LocalDate dataDeNascimento,
        String pais
) {
}
