package com.example;

import com.example.exceptions.BadRequestEx;
import com.example.exceptions.PersonNotFoundEx;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllPersons();
    Person getPerson(int id) throws PersonNotFoundEx;
    Person updatePerson(Person person) throws PersonNotFoundEx;
    boolean deletePerson(int id) throws PersonNotFoundEx;
    Person addPerson(Person person) throws BadRequestEx;

    int countPersons();
}
