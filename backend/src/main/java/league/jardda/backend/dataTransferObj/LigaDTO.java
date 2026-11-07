package league.jardda.backend.dataTransferObj;

public record LigaDTO (
        long ligaId,
        String nomeLiga,
        String paisLiga,
        String estiloLiga,
        String categoriaLiga
){}
