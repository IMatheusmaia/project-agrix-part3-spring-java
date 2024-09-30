package com.betrybe.agrix.controller.exception;

/**
 * The type Farm not found exception.
 */
public class FarmNotFoundException extends Exception {
  @Override
  public String getMessage() {
    return "Fazenda não encontrada!";
  }
}
