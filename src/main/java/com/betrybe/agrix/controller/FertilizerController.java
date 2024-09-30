package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDtoCreated;
import com.betrybe.agrix.controller.dto.FertilizerDtoResponse;
import com.betrybe.agrix.controller.exception.FertilizerNotFoundException;
import com.betrybe.agrix.entity.FertilizerEntity;
import com.betrybe.agrix.service.FertilizerService;
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
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create response entity.
   *
   * @param fertilizerDtoCreated the fertilizer dto created
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<FertilizerDtoResponse> create(
          @RequestBody FertilizerDtoCreated fertilizerDtoCreated) {
    FertilizerEntity newFertilizer = fertilizerService.create(
            FertilizerDtoCreated.toEntity(fertilizerDtoCreated));

    return ResponseEntity.status(HttpStatus.CREATED).body(
            FertilizerDtoResponse.fromEntity(newFertilizer)
    );
  }

  /**
   * Gets all.
   *
   * @return the all
   */
  @GetMapping
  public ResponseEntity<List<FertilizerDtoResponse>> getAll() {
    return ResponseEntity.ok().body(
            fertilizerService.getAll().stream()
                    .map(FertilizerDtoResponse::fromEntity)
                    .toList()
    );
  }

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDtoResponse> getById(@PathVariable Long id)
          throws FertilizerNotFoundException {
    FertilizerEntity fertilizer = fertilizerService.getById(id);

    if (fertilizer == null) {
      throw new FertilizerNotFoundException();
    }
    return ResponseEntity.ok().body(FertilizerDtoResponse.fromEntity(fertilizer));
  }
}
