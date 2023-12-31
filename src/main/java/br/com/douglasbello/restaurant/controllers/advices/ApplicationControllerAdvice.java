package br.com.douglasbello.restaurant.controllers.advices;

import br.com.douglasbello.restaurant.exceptions.EntityNotFoundException;
import br.com.douglasbello.restaurant.model.dtos.ExceptionResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponseDTO> handleInvalidEnumException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(HttpStatus.BAD_REQUEST.value(), "The type in one of the fields was not the right type."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errorList.add(errorMessage);
        });
        return ResponseEntity.badRequest().body(new ExceptionResponseDTO(HttpStatus.BAD_REQUEST.value(), errorList.get(0)));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUniqueIndexException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponseDTO(HttpStatus.CONFLICT.value(), "A field provided is unique and already in use."));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponseDTO> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(HttpStatus.BAD_REQUEST.value(), "You provided the wrong data type in the field: " + exception.getPropertyName()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAccessDeniedException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponseDTO(HttpStatus.FORBIDDEN.value(), "Access denied."));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAuthenticationException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponseDTO(HttpStatus.UNAUTHORIZED.value(), "Authentication failed."));
    }
}
