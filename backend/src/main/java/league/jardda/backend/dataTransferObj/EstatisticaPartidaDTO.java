package league.jardda.backend.dataTransferObj;

public record EstatisticaPartidaDTO(
        Long partidaId,
        Integer clubeCasaGols,
        Integer clubeForaGols,
        Integer clubeCasaCartoes,
        Integer clubeForaCartoes,
        Integer clubeCasaChutes,
        Integer clubeForaChutes
) {
}
