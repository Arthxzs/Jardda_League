package league.jardda.backend.dataTransferObj;

import java.time.LocalDate;

public record PartidaDTO(
        Long id,
        Long clubeCasaId,
        String clubeCasa,
        Long clubeForaId,
        String clubeFora,
        String liga,
        String estadio,
        LocalDate data,
        Integer publico,
        Integer rodada
) {
}
