package kg.attractor.jobsearch.exeptions.advice;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch.exeptions.*;
import kg.attractor.jobsearch.servise.mainServises.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(NotFound.class)
    public String handleNoSuchElementException(Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String NullPointExep(Model model, HttpServletRequest request) {
//        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
//        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
//        model.addAttribute("details", request);
//        return "errors/error";
        return "redirect:/auth/login";
    }

    @ExceptionHandler(NotOwnVacancy.class)
    public String handleNotOwnVacancy() {
        return "redirect:/profile";
    }

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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ErrorResponseBody> alreadyExist (HttpMessageNotReadableException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.makeResponse(e, "wrong account type", HttpStatus.BAD_REQUEST));
    }

}
