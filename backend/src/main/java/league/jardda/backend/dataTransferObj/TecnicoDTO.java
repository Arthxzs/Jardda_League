package league.jardda.backend.dataTransferObj;

import java.time.LocalDate;

public record TecnicoDTO (
        long tecnicoId,
        String nomeTecnico,
        LocalDate aniversarioTecnico,
        String paisTecnico
){}
