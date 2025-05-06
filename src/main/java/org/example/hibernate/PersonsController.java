package org.example.hibernate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("persons")
public class PersonsController {
    private final PersonsRepository personsRepository;

    public PersonsController(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    @GetMapping("by-city")
    public List<Person> getPersonsByCity(@RequestParam String city){
        return personsRepository.findByCityOfLiving(city);
    }

    @GetMapping("by-age")
    public List<Person> getPersonsByAge(@RequestParam Integer age){
        return personsRepository.findByAccountAgeLessThanOrderByAccountAgeAsc(age);
    }

    @GetMapping("by-name-surname")
    public Optional<Person> getPersonsByAge(@RequestParam String name, String surname){
        return personsRepository.findByAccountNameAndAccountSurname(name, surname);
    }

}
