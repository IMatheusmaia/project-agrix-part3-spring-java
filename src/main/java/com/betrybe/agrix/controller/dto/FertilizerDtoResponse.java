package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.FertilizerEntity;

/**
 * The type Fertilizer dto response.
 */
public record FertilizerDtoResponse(Long id, String name,
                                    String brand, String composition) {

  /**
   * From entity fertilizer dto response.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer dto response
   */
  public static FertilizerDtoResponse fromEntity(FertilizerEntity fertilizer) {
    return new FertilizerDtoResponse(
            fertilizer.getId(), fertilizer.getName(),
            fertilizer.getBrand(), fertilizer.getComposition());
  }
}
