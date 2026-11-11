package league.jardda.backend.dataTransferObj;

public record EstadioDTO(
        Long id,
        String nome,
        String cidade,
        Long clubeId,
        String clubeNome
) {
}
