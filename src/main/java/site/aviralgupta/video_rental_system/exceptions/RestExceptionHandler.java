package site.aviralgupta.video_rental_system.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.aviralgupta.video_rental_system.exceptions.exceptions.EntityNotFoundException;
import site.aviralgupta.video_rental_system.exceptions.exceptions.UserAlreadyExistsException;

import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> HandleException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> HandleEntityNotFoundException(UserAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> HandleUsernameNotFoundException(UsernameNotFoundException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "some text"));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> HandleBadCredentialsException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> HandleEntityNotFoundException(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
    }

}
