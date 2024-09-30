package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.FarmEntity;

/**
 * The type Farm dto response.
 */
public record FarmDtoResponse(Long id, String name, Double size) {
  public static FarmDtoResponse fromEntity(FarmEntity farmEntity) {
    return new FarmDtoResponse(farmEntity.getId(), farmEntity.getName(), farmEntity.getSize());
  }
}
