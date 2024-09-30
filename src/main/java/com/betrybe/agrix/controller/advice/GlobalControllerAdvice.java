package com.betrybe.agrix.controller.advice;

import com.betrybe.agrix.controller.exception.CropBadRequestException;
import com.betrybe.agrix.controller.exception.CropNotFoundException;
import com.betrybe.agrix.controller.exception.FarmBadRequestException;
import com.betrybe.agrix.controller.exception.FarmNotFoundException;
import com.betrybe.agrix.controller.exception.FertilizerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Controller advice.
 */
@ControllerAdvice
public class GlobalControllerAdvice {
  @ExceptionHandler(FarmBadRequestException.class)
  public ResponseEntity<String> handleFarmBadRequest(FarmBadRequestException exception) {
    return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(exception.getMessage());
  }

  @ExceptionHandler(CropBadRequestException.class)
  public ResponseEntity<String> handleCropBadRequest(CropBadRequestException exception) {
    return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(exception.getMessage());
  }

  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleFarmNotFoundException(FarmNotFoundException exception) {
    return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(exception.getMessage());
  }

  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(CropNotFoundException exception) {
    return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(exception.getMessage());
  }

  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handleFertilizerNotFoundException(
          FertilizerNotFoundException exception) {
    return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(exception.getMessage());
  }
}
