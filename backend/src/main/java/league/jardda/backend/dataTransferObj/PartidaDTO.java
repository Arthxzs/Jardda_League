package league.jardda.backend.dataTransferObj;

import java.time.LocalDate;

public record PartidaDTO(
        long partidaId,
        String clubeCasa,
        String clubeFora,
        String ligaPartida,
        String estadioPartida,
        LocalDate dataPartida,
        long publicoPartida,
        int rodadaPartida
) {
}
