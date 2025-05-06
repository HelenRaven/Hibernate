package org.example.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<Person, Account> {
    public List<Person> findByCityOfLiving(String city);

    public List<Person> findByAccountAgeLessThanOrderByAccountAgeAsc(Integer age);

    public Optional<Person> findByAccountNameAndAccountSurname(String name, String surname);
}
