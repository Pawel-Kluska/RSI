package com.example.server;


import com.example.server.exceptions.BadRequestEx;
import com.example.server.exceptions.PersonNotFoundEx;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.minidev.json.JSONObject;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository repository = new PersonRepositoryImpl();

    @GetMapping
    public CollectionModel<EntityModel<Person>> getAllPersons() {
        System.out.println("...called GET");
        List<EntityModel<Person>> persons =
                repository.getAllPersons().stream().map(person ->
                        EntityModel.of(person,
                                linkTo(methodOn(PersonController.class)
                                        .getPerson(person.getId())).withRel("get"),
                                linkTo(methodOn(PersonController.class)
                                        .deletePerson(person.getId())).withRel("delete"),
                                linkTo(methodOn(PersonController.class)
                                        .getAllPersons()).withRel("list all")
                        )
                ).toList();
        return CollectionModel.of(persons,
                linkTo(methodOn(PersonController.class)
                        .getAllPersons()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Person> getPerson(@PathVariable int id) {
        System.out.println("...called GET");
        try {
            Person person = repository.getPerson(id);
            return EntityModel.of(person,
                    linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel(),
                    linkTo(methodOn(PersonController.class).deletePerson(person.getId())).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getAllPersons()).withRel("list all")
            );
        } catch (PersonNotFoundEx e) {
            System.out.println("...GET Exception");
            throw e;
        }
    }

    @PostMapping
    public EntityModel<Person> addPerson(@RequestBody Person person) {
        try {
            System.out.println("...called POST");
            Person addedPerson = repository.addPerson(person);
            return EntityModel.of(person,
                    linkTo(methodOn(PersonController.class).getPerson(addedPerson.getId())).withRel("get"),
                    linkTo(methodOn(PersonController.class).deletePerson(addedPerson.getId())).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getAllPersons()).withRel("list all")
            );
        } catch (BadRequestEx e) {
            System.out.println("...POST Exception");
            throw e;
        }
    }

    @PutMapping
    public EntityModel<Person> updatePerson(@RequestBody Person person) {
        System.out.println("...called PUT");
        try {
            Person updatedPerson = repository.updatePerson(person);
            return EntityModel.of(person,
                    linkTo(methodOn(PersonController.class).getPerson(updatedPerson.getId())).withRel("get"),
                    linkTo(methodOn(PersonController.class).deletePerson(updatedPerson.getId())).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getAllPersons()).withRel("list all")
            );
        } catch (PersonNotFoundEx | BadRequestEx e) {
            System.out.println("...PUT Exception");
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public EntityModel<JSONObject> deletePerson(@PathVariable int id) {
        System.out.println("...called DELETE");
        try {
            boolean deleted = repository.deletePerson(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("isDeleted",deleted );

            return EntityModel.of(jsonObject,
                    linkTo(methodOn(PersonController.class).getPerson(id)).withRel("get"),
                    linkTo(methodOn(PersonController.class).deletePerson(id)).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getAllPersons()).withRel("list all")
            );
        } catch (PersonNotFoundEx e) {
            System.out.println("...DELETE Exception");
            throw e;
        }
    }
}
