package ftn.isa.repository;

import ftn.isa.domain.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public interface IPersonRepository {

    Collection<Person> findAll();

    Person create(Person person);

    Person findOne(Long id);

    Person update(Person person);

    Person delete(Long id);

}
