package learn.organizer.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // DataAccessException is the super class of many Spring database exceptions
    // including BadSqlGrammarException.
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleException(DataAccessException ex) {
        return new ResponseEntity<String>(
                "Something went wrong in our database. Try again later",
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // IllegalArgumentException is the super class for many Java exceptions
    // including all formatting (number, date) exceptions.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException ex) {

        return new ResponseEntity<String>(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleException(HttpRequestMethodNotSupportedException ex) {

        return new ResponseEntity<String>(
                "Required Parameter missing",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleException(DataIntegrityViolationException ex) {

        return new ResponseEntity<String>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException ex) {

        return new ResponseEntity<String>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleException(DuplicateKeyException ex) {

        return new ResponseEntity<String>(
                "Something went wrong in our database. Try again later",
                HttpStatus.BAD_REQUEST);
    }
   // @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
   // public ResponseEntity<String> handleException(HttpMediaTypeNotSupportedException ex) {

   //     return new ResponseEntity<String>(
   //             ex.getMessage(),
   //             HttpStatus.BAD_REQUEST);
    //}
    // This is our absolute last resort. Yuck.
//    @ExceptionHandler(Exception.class)
  //  public ResponseEntity<String> handleException(Exception ex) {

    //    return new ResponseEntity<String>(
      //          "Something went wrong on our end. Your request failed. :(",
        //        HttpStatus.INTERNAL_SERVER_ERROR);
//}
}