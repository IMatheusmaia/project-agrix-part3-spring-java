package com.betrybe.agrix.service;

import com.betrybe.agrix.controller.exception.CropNotFoundException;
import com.betrybe.agrix.controller.exception.FertilizerNotFoundException;
import com.betrybe.agrix.entity.CropEntity;
import com.betrybe.agrix.entity.FertilizerEntity;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository,
                     FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public CropEntity createCrop(CropEntity crop) {
    return cropRepository.save(crop);
  }

  public List<CropEntity> getAllCrops() {
    return cropRepository.findAll();
  }

  public CropEntity getCropById(Long id) {
    return cropRepository.findById(id).orElse(null);
  }

  /**
   * Gets crops by period.
   *
   * @param start the start
   * @param end   the end
   * @return the crops by period
   */
  public List<CropEntity> getCropsByPeriod(String start, String end) {
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);

    return cropRepository.findAllByHarvestDateBetween(startDate, endDate);
  }

  /**
   * Associate crop to fertilizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public void associateCropToFertilizer(Long cropId, Long fertilizerId)
          throws CropNotFoundException, FertilizerNotFoundException {

    CropEntity crop = getCropById(cropId);
    if (crop == null) {
      throw new CropNotFoundException();
    }

    FertilizerEntity fertilizer = fertilizerRepository.findById(fertilizerId)
            .orElse(null);

    if (fertilizer == null) {
      throw new FertilizerNotFoundException();
    }

    crop.setFertilizers(List.of(fertilizer));
    cropRepository.save(crop);
  }
}
