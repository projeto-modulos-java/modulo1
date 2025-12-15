package com.projeto.modulo1.controllers.exception_handler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projeto.modulo1.services.exceptions.UserException;

@ControllerAdvice
public class ExceptionHandlerControler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(){
        return ResponseEntity.status(500).build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElement(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleUserEntity(UserException ex, Model model){
        return  ResponseEntity.status(ex.getStatus()).body(new Error(ex.getMessage(), LocalDateTime.now()));
    }
}
