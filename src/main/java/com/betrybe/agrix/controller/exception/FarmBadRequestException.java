package com.betrybe.agrix.controller.exception;

/**
 * The type Farm bad request exception.
 */
public class FarmBadRequestException extends Exception {

  @Override
  public String getMessage() {
    return "Os valores de Farm não podem ser nulos ou vazios";
  }
}
