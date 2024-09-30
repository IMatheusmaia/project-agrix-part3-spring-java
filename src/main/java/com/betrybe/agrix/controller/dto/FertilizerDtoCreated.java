package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.FertilizerEntity;

/**
 * The type Fertilizer dto created.
 */
public record FertilizerDtoCreated(String  name, String brand, String composition) {
  /**
   * To entity fertilizer entity.
   *
   * @param fertilizerDtoCreated the fertilizer dto created
   * @return the fertilizer entity
   */
  public static FertilizerEntity toEntity(FertilizerDtoCreated fertilizerDtoCreated) {

    FertilizerEntity fertilizer = new FertilizerEntity();
    fertilizer.setName(fertilizerDtoCreated.name());
    fertilizer.setBrand(fertilizerDtoCreated.brand());
    fertilizer.setComposition(fertilizerDtoCreated.composition());
    return fertilizer;
  }
}
