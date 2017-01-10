package ir.sk.patienttalk.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Random;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class BaseController {

    Random random = new Random();

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,  reason="Illegal request, please verify your payload")
    public void handleClientErrors(Exception ex) { }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal server error")
    public void handleServerErrors(Exception ex) {	}
}
