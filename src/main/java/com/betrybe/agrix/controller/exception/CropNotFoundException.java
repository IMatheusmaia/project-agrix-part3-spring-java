package com.betrybe.agrix.controller.exception;

/**
 * The type Farm not found exception.
 */
public class CropNotFoundException extends Exception {
  @Override
  public String getMessage() {
    return "Plantação não encontrada!";
  }
}
