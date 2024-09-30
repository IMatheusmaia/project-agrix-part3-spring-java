package com.betrybe.agrix.solution;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Testa as funcionalidades da camada de serviço Person")
public class PersonServiceTest {

  @MockBean
  PersonRepository personRepository;

  @Autowired
  PersonService personService;

  @Test
  @DisplayName("Testa a busca de uma pessoa pelo Id")
  public void testGetPersonById() {
    Person person = new Person();
    person.setId(100L);
    person.setUsername("Matheus");
    person.setPassword("123456");
    person.setRole(Role.ADMIN);

    when(personRepository.findById(eq(100L))).thenReturn(Optional.of(person));

    Person response = personService.getPersonById(100L);
    verify(personRepository).findById(eq(100L));

    assert response.getId().equals(person.getId());
    assert response.getUsername().equals(person.getUsername());
    assert response.getPassword().equals(person.getPassword());
    assert response.getRole().equals(person.getRole());
  }

  @Test
  @DisplayName("Testa a busca de uma pessoa pelo nome de usuário")
  public void testGetPersonByUsername() {
    Person person = new Person();
    person.setId(123L);
    person.setUsername("Isabel");
    person.setPassword("123456");
    person.setRole(Role.USER);

    when(personRepository.findByUsername(eq("Isabel"))).thenReturn(Optional.of(person));
    Person response = personService.getPersonByUsername("Isabel");
    verify(personRepository).findByUsername(eq("Isabel"));

    assert response.getId().equals(person.getId());
    assert response.getUsername().equals(person.getUsername());
    assert response.getPassword().equals(person.getPassword());
    assert response.getRole().equals(person.getRole());
  }

  @Test
  @DisplayName("Testa a criação de uma pessoa")
  public void testCreatePerson() {
    Person person = new Person();
    person.setId(100L);
    person.setUsername("Isabel");
    person.setPassword("123456");
    person.setRole(Role.USER);

    when(personRepository.save(any())).thenReturn(person);
    Person response = personService.create(person);

    verify(personRepository).save(person);

    assert response.getId().equals(person.getId());
    assert response.getUsername().equals(person.getUsername());
    assert response.getPassword().equals(person.getPassword());
    assert response.getRole().equals(person.getRole());
  }
}
