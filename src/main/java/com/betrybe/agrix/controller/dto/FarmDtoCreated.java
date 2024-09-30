package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.FarmEntity;

/**
 * The type Farm dto created.
 */
public record FarmDtoCreated(String name, Double size) {
  /**
   * To entity farm entity.
   *
   * @param farmDto the farm dto
   * @return the farm entity
   */
  public static FarmEntity toEntity(FarmDtoCreated  farmDto) {

    FarmEntity farm = new FarmEntity();
    farm.setName(farmDto.name());
    farm.setSize(farmDto.size());
    return farm;
  }
}
