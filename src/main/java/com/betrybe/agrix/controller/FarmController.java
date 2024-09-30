package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDtoCreated;
import com.betrybe.agrix.controller.dto.CropDtoResponse;
import com.betrybe.agrix.controller.dto.FarmDtoCreated;
import com.betrybe.agrix.controller.dto.FarmDtoResponse;
import com.betrybe.agrix.controller.exception.CropBadRequestException;
import com.betrybe.agrix.controller.exception.FarmBadRequestException;
import com.betrybe.agrix.controller.exception.FarmNotFoundException;
import com.betrybe.agrix.entity.CropEntity;
import com.betrybe.agrix.entity.FarmEntity;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Gets farms.
   *
   * @return the farms
   */
  @GetMapping
  public ResponseEntity<List<FarmDtoResponse>> getFarms() {
    List<FarmEntity> farms = farmService.getAllFarms();
    return ResponseEntity.ok().body(farms.stream()
            .map(FarmDtoResponse::fromEntity)
            .toList());
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDtoResponse> getFarmById(@PathVariable Long id)
          throws FarmNotFoundException {
    FarmEntity farm = farmService.getFarmById(id);
    if (farm == null) {
      throw new FarmNotFoundException();
    }
    return ResponseEntity.ok().body(FarmDtoResponse.fromEntity(farm));
  }

  /**
   * Create farm response entity.
   *
   * @param farmDto the farm dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<FarmDtoResponse> createFarm(@RequestBody FarmDtoCreated farmDto)
          throws FarmBadRequestException {
    if (farmDto.name() == null || farmDto.name().isEmpty()) {
      throw new FarmBadRequestException();
    } else if (farmDto.size() == null || farmDto.size() <= 0) {
      throw new FarmBadRequestException();
    }
    FarmEntity farmResponsedb = farmService.createFarm(FarmDtoCreated.toEntity(farmDto));
    FarmDtoResponse farmResponse = FarmDtoResponse.fromEntity(farmResponsedb);
    return ResponseEntity.status(HttpStatus.CREATED).body(farmResponse);
  }

  /**
   * Associate crop to farm response entity.
   *
   * @param farmId  the farm id
   * @param cropDto the crop dto
   * @return the response entity
   * @throws CropBadRequestException the crop bad request exception
   * @throws FarmNotFoundException   the farm not found exception
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDtoResponse> associateCropToFarm(
          @PathVariable Long farmId,
          @RequestBody CropDtoCreated cropDto)
          throws CropBadRequestException,
          FarmNotFoundException {

    if (cropDto.name() == null || cropDto.name().isEmpty()) {
      throw new CropBadRequestException();
    } else if (cropDto.plantedArea() == null || cropDto.plantedArea() <= 0) {
      throw new CropBadRequestException();
    }

    FarmEntity farm = farmService.getFarmById(farmId);

    if (farm == null) {
      throw new FarmNotFoundException();
    }

    CropEntity newCrop = cropService.createCrop(CropDtoCreated.toEntity(cropDto, farm));
    farm.setCrops(List.of(newCrop));

    return ResponseEntity.status(HttpStatus.CREATED).body(CropDtoResponse.fromEntity(newCrop));
  }

  /**
   * Gets crops by farm id.
   *
   * @param farmId the farm id
   * @return the crops by farm id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDtoResponse>> getCropsByFarmId(
          @PathVariable Long farmId) throws FarmNotFoundException {
    List<CropEntity> crops = farmService.getCropsByFarmId(farmId);
    if (crops == null) {
      throw new FarmNotFoundException();
    }
    return ResponseEntity.ok().body(
            crops.stream().map(CropDtoResponse::fromEntity).toList());
  }
}
