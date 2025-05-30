package ing.beribtur.config.exception.handler;

import ing.beribtur.accent.message.FailureMessage;
import ing.beribtur.accent.message.FailureResponse;
import ing.beribtur.config.exception.exception.EntityAlreadyExistsException;
import ing.beribtur.config.exception.exception.EntityNotFoundException;
import ing.beribtur.config.exception.exception.RoleNotFoundException;
import ing.beribtur.config.exception.exception.UserAlreadyExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<FailureResponse> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        //
        FailureResponse failureResponse = new FailureResponse(new FailureMessage(exception.getMessage()));

        return new ResponseEntity<>(failureResponse, exception.getHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<FailureResponse> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        //
        FailureResponse failureResponse = new FailureResponse(new FailureMessage("UserAlreadyExistsException", exception.getMessage(), "409"));
        return new ResponseEntity<>(failureResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<FailureResponse> roleNotFoundExceptionHandler(RoleNotFoundException exception) {
        //
        FailureResponse failureResponse = new FailureResponse(new FailureMessage("RoleNotFoundException", exception.getMessage(), "404"));
        return new ResponseEntity<>(failureResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<FailureResponse> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        //
        FailureResponse failureResponse = new FailureResponse(new FailureMessage("IllegalArgumentException", exception.getMessage(), "400"));
        return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EntityAlreadyExistsException.class)
    public ResponseEntity<FailureResponse> entityAlreadyExistsExceptionHandler(EntityAlreadyExistsException exception) {
        //
        FailureResponse failureResponse = new FailureResponse(new FailureMessage("EntityAlreadyExistsException", exception.getMessage(), "409"));
        return new ResponseEntity<>(failureResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<FailureResponse> entityNotFoundExceptionHandler(EntityNotFoundException exception) {
        //
        FailureResponse failureResponse = new FailureResponse(new FailureMessage("EntityNotFoundException", exception.getMessage(), "400"));
        return new ResponseEntity<>(failureResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AuthorizationDeniedException.class)
    public ResponseEntity<FailureResponse> authDeniedExceptionHandler(AuthorizationDeniedException exception) {
        //
        FailureResponse failureResponse = new FailureResponse(new FailureMessage(exception.getMessage()));

        return new ResponseEntity<>(failureResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<FailureResponse> generalExceptionHandler(Exception exception) {
        //
        FailureResponse failureResponse = new FailureResponse(new FailureMessage(exception.getMessage()));
        exception.printStackTrace();
        return new ResponseEntity<>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
