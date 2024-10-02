package com.betrybe.agrix.ebytr.staff.exception;

/**
 * The type Person already exists exception.
 */
public class PersonAlreadyExistsException extends Exception {
  @Override
  public String getMessage() {
    return "Person already exists";
  }
}
