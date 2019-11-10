package com.eliseev.app.exeption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice("com.eliseev.app.controllers.controller")
public class ControllerExceptionsHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionsHandler.class);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleMethodArgEx(MethodArgumentTypeMismatchException ex, Model model) {
        logger.warn("an error occurred: {} should be of type {}", ex.getName(), ex.getRequiredType().getName());
        model.addAttribute("errors",ex.getName() + " should be of type " + ex.getRequiredType().getName());
        return "error/error";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(ConstraintViolationException ex, Model model) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        logger.warn("errors occurred: {}", errors);
        model.addAttribute("errors", errors);

        return "error/error";
    }



}
