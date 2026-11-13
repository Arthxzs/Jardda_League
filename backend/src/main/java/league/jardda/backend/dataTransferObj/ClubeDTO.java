package league.jardda.backend.dataTransferObj;


public record ClubeDTO(
        Long id,
        String nome,
        String cidade,
        String pais,
        String siglaPais
) {
}
