package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDtoResponse;
import com.betrybe.agrix.controller.dto.FertilizerDtoResponse;
import com.betrybe.agrix.controller.exception.CropNotFoundException;
import com.betrybe.agrix.controller.exception.FertilizerNotFoundException;
import com.betrybe.agrix.entity.CropEntity;
import com.betrybe.agrix.entity.FertilizerEntity;
import com.betrybe.agrix.service.CropService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public ResponseEntity<List<CropDtoResponse>> getAllCrops() {
    return ResponseEntity.ok().body(cropService.getAllCrops().stream()
            .map(CropDtoResponse::fromEntity)
            .toList());
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDtoResponse> getCropById(@PathVariable Long id)
          throws CropNotFoundException {
    CropEntity crop = cropService.getCropById(id);
    if (crop == null) {
      throw new CropNotFoundException();
    }
    return ResponseEntity.ok().body(CropDtoResponse.fromEntity(crop));
  }

  /**
   * Gets crops by period.
   *
   * @param start the start
   * @param end   the end
   * @return the crops by period
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDtoResponse>> getCropsByPeriod(
          @RequestParam String start, @RequestParam String end
  ) {
    return ResponseEntity.ok().body(
            cropService.getCropsByPeriod(start, end).stream()
            .map(CropDtoResponse::fromEntity)
            .toList());
  }

  /**
   * Associate crop to fertilizer response entity.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the response entity
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropToFertilizer(
          @PathVariable Long cropId, @PathVariable Long fertilizerId
  ) throws CropNotFoundException, FertilizerNotFoundException {

    cropService.associateCropToFertilizer(cropId,
            fertilizerId);

    return ResponseEntity.status(HttpStatus.CREATED).body(
            "Fertilizante e plantação associados com sucesso!"
    );
  }

  /**
   * Gets fertilizers by crop id.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDtoResponse>> getFertilizersByCropId(
          @PathVariable Long cropId)
          throws CropNotFoundException, FertilizerNotFoundException {

    CropEntity response = cropService.getCropById(cropId);

    if  (response == null) {
      throw new CropNotFoundException();
    }

    List<FertilizerEntity> fertilizers = response.getFertilizers();

    return ResponseEntity.ok().body(fertilizers.stream()
            .map(FertilizerDtoResponse::fromEntity)
            .toList());
  }
}
