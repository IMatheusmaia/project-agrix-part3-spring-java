package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.CropEntity;
import com.betrybe.agrix.entity.FarmEntity;
import com.betrybe.agrix.repository.FarmRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public FarmEntity createFarm(FarmEntity farm) {
    return farmRepository.save(farm);
  }

  public List<FarmEntity> getAllFarms() {
    return farmRepository.findAll();
  }

  public FarmEntity getFarmById(Long id) {
    return farmRepository.findById(id).orElse(null);
  }

  /**
   * Gets crops by farm id.
   *
   * @param farmId the farm id
   * @return the crops by farm id
   */
  public List<CropEntity> getCropsByFarmId(Long farmId) {
    FarmEntity farm = farmRepository.findById(farmId).orElse(null);
    if (farm != null) {
      return farm.getCrops();
    }
    return null;
  }
}
