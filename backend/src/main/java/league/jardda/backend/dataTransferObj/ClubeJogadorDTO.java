package league.jardda.backend.dataTransferObj;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClubeJogadorDTO(
        Long id,
        Long jogadorId,
        String jogadorNome,
        LocalDate inicioContrato,
        LocalDate previsaoTermino,
        LocalDate terminoContrato,
        BigDecimal valorSalario
) {}
