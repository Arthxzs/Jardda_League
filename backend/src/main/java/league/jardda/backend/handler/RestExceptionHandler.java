package league.jardda.backend.handler;

import league.jardda.backend.exception.ExceptionDetails;
import league.jardda.backend.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> notFoundExceptionHandler(NotFoundException notFound) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                "Recurso não encontrado",
                HttpStatus.NOT_FOUND.value(),
                notFound.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                exceptionDetails,
                HttpStatus.NOT_FOUND
        );
    }
}
