package com.jsonfloyd.controller;

        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;

        import java.sql.SQLException;

@ControllerAdvice
public class OrderControllerAdvice {
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> sqlExceptionHandler(SQLException e){
        return new ResponseEntity<String>("handled SQLException" ,HttpStatus.NOT_IMPLEMENTED);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> commonExceptionHandler(Exception e){
        return new ResponseEntity<String>("catch ya", HttpStatus.NOT_IMPLEMENTED);
    }

}
