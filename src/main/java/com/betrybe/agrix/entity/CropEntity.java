package com.betrybe.agrix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Crop entity.
 */
@Entity
@Table(name = "crops")
public class CropEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Double plantedArea;

  private LocalDate plantedDate;
  private LocalDate harvestDate;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private FarmEntity farm;

  @ManyToMany
  @JoinTable(
          name = "crop_fertilizer",
          joinColumns = @JoinColumn(name = "fertilizer_id"),
          inverseJoinColumns = @JoinColumn(name = "crop_id")
  )
  private List<FertilizerEntity> fertilizers = new ArrayList<>();

  public CropEntity() {
  }

  /**
   * Instantiates a new Crop entity.
   *
   * @param id          the id
   * @param name        the name
   * @param plantedArea the planted area
   * @param plantedDate the planted date
   * @param harvestDate the harvest date
   */
  public CropEntity(Long id, String name, Double plantedArea,
                    LocalDate plantedDate, LocalDate harvestDate) {
    this.id = id;
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPlantedArea() {
    return plantedArea;
  }

  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  public FarmEntity getFarm() {
    return farm;
  }

  public void setFarm(FarmEntity farm) {
    this.farm = farm;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(String plantedDate) {
    this.plantedDate = LocalDate.parse(plantedDate);
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(String harvestDate) {
    this.harvestDate = LocalDate.parse(harvestDate);
  }

  public List<FertilizerEntity> getFertilizers() {
    return fertilizers;
  }

  public void setFertilizers(List<FertilizerEntity> fertilizers) {
    this.fertilizers.addAll(fertilizers);
  }
}
