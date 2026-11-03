package league.jardda.backend.dataTransferObj;


public record ClubeDTO (
        long clubeId,
        String nomeClube,
        String cidadeClube,
        String paisClube,
        String siglaPais
) {}
