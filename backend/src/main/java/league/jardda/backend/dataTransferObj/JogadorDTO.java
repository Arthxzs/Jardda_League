package league.jardda.backend.dataTransferObj;

import java.time.LocalDate;

public record JogadorDTO (
        long jogadorId,
        String nomeJogador,
        String paisJogador,
        LocalDate aniversarioJogador,
        String posicao,
        boolean lesionado
){}
