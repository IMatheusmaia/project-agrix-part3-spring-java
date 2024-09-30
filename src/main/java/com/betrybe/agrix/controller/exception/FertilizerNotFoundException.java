package com.betrybe.agrix.controller.exception;

/**
 * The type Fertilizer not found exception.
 */
public class FertilizerNotFoundException extends Exception {
  @Override
  public String getMessage() {
    return "Fertilizante não encontrado!";
  }
}
