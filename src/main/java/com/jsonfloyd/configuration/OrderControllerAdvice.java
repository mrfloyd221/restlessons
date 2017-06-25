package com.jsonfloyd.configuration;

        import lombok.extern.java.Log;
        import lombok.extern.log4j.Log4j;
        import lombok.extern.log4j.Log4j2;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;

        import java.sql.SQLException;

@ControllerAdvice
@Log4j
public class OrderControllerAdvice {
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> sqlExceptionHandler(SQLException e){
        log.error(e.getMessage());
        return new ResponseEntity<String>("handled SQLException" ,HttpStatus.NOT_IMPLEMENTED);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> commonExceptionHandler(Exception e){
        log.error(e.getMessage());
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
    }

}
