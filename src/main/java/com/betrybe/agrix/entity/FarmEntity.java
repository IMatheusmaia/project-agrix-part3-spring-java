package com.betrybe.agrix.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * The type Farm entity.
 */
@Entity
@Table(name = "farms")
public class FarmEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Double size;

  @OneToMany(mappedBy = "farm")
  private List<CropEntity> crops;

  public FarmEntity() {
  }

  /**
   * Instantiates a new Farm entity.
   *
   * @param id   the id
   * @param name the name
   * @param size the size
   */
  public FarmEntity(Long id, String name, Double size) {
    this.id = id;
    this.name = name;
    this.size = size;
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

  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }


  public List<CropEntity> getCrops() {
    return crops;
  }

  public void setCrops(List<CropEntity> crops) {
    this.crops = crops;
  }
}
