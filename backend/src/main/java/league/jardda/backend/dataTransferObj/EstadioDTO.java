package league.jardda.backend.dataTransferObj;

public record EstadioDTO(
        long estadioId,
        String nomeEstadio,
        String cidadeEstadio,
        long clubeId,
        String clubeNome
) {
}
