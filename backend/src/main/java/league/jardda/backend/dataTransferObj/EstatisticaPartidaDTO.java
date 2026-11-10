package league.jardda.backend.dataTransferObj;

public record EstatisticaPartidaDTO(
        Long partidaId,
        Integer time1Gols,
        Integer time2Gols,
        Integer time1Cartoes,
        Integer time2Cartoes,
        Integer time1Chutes,
        Integer time2Chutes
) {}
