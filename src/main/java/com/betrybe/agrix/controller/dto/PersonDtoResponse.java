package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;

/**
 * The type Person response.
 */
public record PersonDtoResponse(Long id, String username, String role) {
  public static PersonDtoResponse fromEntity(Person person) {
    return new PersonDtoResponse(person.getId(), person.getUsername(),
            person.getRole().toString());
  }
}
