package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.PersonDtoCreated;
import com.betrybe.agrix.controller.dto.PersonDtoResponse;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonAlreadyExistsException;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  /**
   * Instantiates a new Person controller.
   *
   * @param personService the person service
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create user response entity.
   *
   * @param personDtoCreated the person dto created
   * @return the response entity
   * @throws PersonAlreadyExistsException the person already exists exception
   */
  @PostMapping
  public ResponseEntity<PersonDtoResponse> createUser(
          @RequestBody PersonDtoCreated personDtoCreated)
          throws PersonAlreadyExistsException {
    Person newPersonResponse = personService.create(
            PersonDtoCreated.toEntity(personDtoCreated));

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(PersonDtoResponse.fromEntity(newPersonResponse));
  }
}
