package kg.attractor.jobsearch.exeptions.advice;

import kg.attractor.jobsearch.exeptions.*;
import kg.attractor.jobsearch.servise.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<ErrorResponseBody> noSuchElementHandler(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorService.makeResponse(e, "", HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<ErrorResponseBody> illegalArgumentHandler(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.makeResponse(e, "invalid request", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponseBody> validHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(errorService.makeResponse(e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFound.class)
    private ResponseEntity<ErrorResponseBody> noSuchElementHandler(NotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorService.makeResponse(e, "not found", HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(EntityForDeleteNotFound.class)
    private ResponseEntity<ErrorResponseBody> noSuchElementHandler(EntityForDeleteNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorService.makeResponse(e, "not found", HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(UsernameNotFound.class)
    private ResponseEntity<ErrorResponseBody> userNotFoundHandler(UsernameNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorService.makeResponse(e, "user not found", HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(UserStatusExeption.class)
    private ResponseEntity<ErrorResponseBody> userStatusExeptionHandler(UserStatusExeption e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.makeResponse(e, "not enough rights", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(AlreadyExists.class)
    private ResponseEntity<ErrorResponseBody> alreadyExist (AlreadyExists e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.makeResponse(e, "already exists", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ErrorResponseBody> alreadyExist (HttpMessageNotReadableException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.makeResponse(e, "wrong account type", HttpStatus.BAD_REQUEST));
    }
}
