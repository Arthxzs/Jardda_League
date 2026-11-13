package league.jardda.backend.dataTransferObj;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClubeTecnicoDTO(
        Long id,
        Long clubeId,
        String clubeNome,
        Long tecnicoId,
        String tecnicoNome,
        LocalDate inicioContrato,
        LocalDate previsaoTermino,
        LocalDate terminoContrato,
        BigDecimal valorSalario
) {
}
