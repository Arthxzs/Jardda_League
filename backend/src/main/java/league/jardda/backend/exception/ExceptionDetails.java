package league.jardda.backend.exception;

import java.time.LocalDateTime;

public record ExceptionDetails (
        String title,
        int status,
        String details,
        LocalDateTime timestamp
) {}
