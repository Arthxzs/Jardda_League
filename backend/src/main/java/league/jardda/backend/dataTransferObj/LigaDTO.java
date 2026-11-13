package league.jardda.backend.dataTransferObj;

public record LigaDTO(
        Long id,
        String nome,
        String pais,
        String estilo,
        String categoria
) {
}
