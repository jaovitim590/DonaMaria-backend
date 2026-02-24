package com.donaMaria_.demo.config;


import com.donaMaria_.demo.exceptions.EmailJaCadastradoException;
import com.donaMaria_.demo.exceptions.RecursoNaoEncontradoException;
import com.donaMaria_.demo.exceptions.RoleInvalidaException;
import com.donaMaria_.demo.exceptions.StatusInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ErrorResponse> handleEmailJaCadastrado(EmailJaCadastradoException e) {
        return build(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleNaoEncontrado(RecursoNaoEncontradoException e) {
        return build(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException e) {
        return build(HttpStatus.FORBIDDEN, "Acesso negado");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return build(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception e) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor");
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(new ErrorResponse(status.value(), message, Instant.now()) {
                    @Override
                    public HttpStatusCode getStatusCode() {
                        return null;
                    }

                    @Override
                    public ProblemDetail getBody() {
                        return null;
                    }
                });
    }

    @ExceptionHandler(RoleInvalidaException.class)
    public ResponseEntity<ErrorResponse> handleRoleInvalida(RoleInvalidaException e) {
        return build(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(StatusInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleStatusInvalido(StatusInvalidoException e){
        return build(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}