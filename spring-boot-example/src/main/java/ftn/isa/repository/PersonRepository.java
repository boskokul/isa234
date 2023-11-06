package ftn.isa.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import ftn.isa.domain.Person;
import org.springframework.stereotype.Repository;


@Repository
public class PersonRepository implements IPersonRepository {

    private static AtomicLong counter = new AtomicLong();

    private final ConcurrentMap<Long, Person> persons = new ConcurrentHashMap<Long, Person>();

    @Override
    public Collection<Person> findAll() {
        return this.persons.values();
    }

    @Override
    public Person create(Person person) {
        Long id = person.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            person.setId(id);
        }

        this.persons.put(id, person);
        return person;
    }

    @Override
    public Person findOne(Long id) {
        return this.persons.get(id);
    }

    @Override
    public Person delete(Long id) {
        Person person = this.persons.remove(id);
        return person;
    }

    @Override
    public Person update(Person person) {
        Long id = person.getId();

        if (id != null) {
            this.persons.put(id, person);
        }

        return person;
    }

}
