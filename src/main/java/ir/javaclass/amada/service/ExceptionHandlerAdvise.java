package ir.javaclass.amada.service;

import ir.javaclass.amada.exception.DataNotFoundException;
import ir.javaclass.amada.exception.TokenNotFoundException;
import ir.javaclass.amada.exception.UserAlreadyExistException;
import ir.javaclass.amada.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-20
 * Time: 13:56
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvise implements RequestBodyAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleServiceException(Exception ex, WebRequest request) {
        if (ex instanceof TokenNotFoundException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else if (ex instanceof UserNotFoundException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else if (ex instanceof DataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (ex instanceof UserAlreadyExistException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
