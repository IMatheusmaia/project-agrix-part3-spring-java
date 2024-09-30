package com.betrybe.agrix.controller.exception;

/**
 * The type Crop bad request exception.
 */
public class CropBadRequestException extends Exception {
  @Override
  public String getMessage() {
    return "Os campos não podem ser nulos ou vazios";
  }
}
