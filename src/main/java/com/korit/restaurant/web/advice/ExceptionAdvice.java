package com.korit.restaurant.web.advice;

import com.korit.restaurant.exception.CustomLikeException;
import com.korit.restaurant.exception.CustomRentalException;
import com.korit.restaurant.exception.CustomValidationException;
import com.korit.restaurant.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationError(CustomValidationException e) {
        return ResponseEntity.badRequest().body(new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "Validation Error", e.getErrorMap()));
    }

    @ExceptionHandler(CustomRentalException.class)
    public ResponseEntity<?> rentalError(CustomRentalException e) {

        return ResponseEntity
                .badRequest()
                .body(new CMRespDto<>(HttpStatus.BAD_REQUEST.value(),
                        "Menu Error",
                        e.getErrorMap()));
    }

    @ExceptionHandler(CustomLikeException.class)
    public ResponseEntity<?> LikeError(CustomLikeException e) {

        return ResponseEntity
                .badRequest()
                .body(new CMRespDto<>(HttpStatus.BAD_REQUEST.value(),
                        "Reservation Error",
                        e.getErrorMap()));
    }
}