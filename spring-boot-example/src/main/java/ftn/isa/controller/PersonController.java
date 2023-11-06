package ftn.isa.controller;

import ftn.isa.domain.Person;
import ftn.isa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(method= RequestMethod.POST , value="/")
    public ResponseEntity<String> createPerson(@Valid @RequestBody Person person) throws ConstraintViolationException {
        this.personService.createPerson(person);
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
        return stringResponseEntity;
    }
}
