package com.example;


import com.example.exceptions.BadRequestEx;
import com.example.exceptions.PersonNotFoundEx;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository repository = new PersonRepositoryImpl();

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        System.out.println("...called GET");
        List<Person> persons = repository.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        System.out.println("...called GET");
        try {
            Person person = repository.getPerson(id);
            return ResponseEntity.ok(person);
        } catch (PersonNotFoundEx e) {
            System.out.println("...GET Exception");
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        try {
            System.out.println("...called POST");
            Person addedPerson = repository.addPerson(person);
            return ResponseEntity.ok(addedPerson);
        } catch (BadRequestEx e) {
            System.out.println("...POST Exception");
            throw e;
        }
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        System.out.println("...called PUT");
        try {
            Person updatedPerson = repository.updatePerson(person);
            return ResponseEntity.ok(updatedPerson);
        } catch (PersonNotFoundEx | BadRequestEx e) {
            System.out.println("...PUT Exception");
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable int id) {
        System.out.println("...called DELETE");
        try {
            boolean result = repository.deletePerson(id);
            return ResponseEntity.ok(result);
        } catch (PersonNotFoundEx e) {
            System.out.println("...DELETE Exception");
            throw e;
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countPersons() {
        System.out.println("...called GET");
        int count = repository.countPersons();
        return ResponseEntity.ok(count);
    }
}
