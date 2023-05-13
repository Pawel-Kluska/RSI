package com.example;

import com.example.exceptions.BadRequestEx;
import com.example.exceptions.PersonNotFoundEx;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    private List<Person> personList;

    public PersonRepositoryImpl() {
        personList = new ArrayList<Person>();
        personList.add(new Person(1, "Jan", 20, "jan@gmail.com"));
        personList.add(new Person(2, "Adam", 25, "adam@gmail.com"));
        personList.add(new Person(3, "Magda", 54, "magda@gmail.com"));
    }

    @Override
    public List<Person> getAllPersons() {
        return personList;
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundEx {
        for (Person thePerson : personList) {
            if (thePerson.getId() == id) {
                return thePerson;
            }
        }
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person updatePerson(Person person) throws PersonNotFoundEx {
        for (Person thePerson : personList) {
            if (thePerson.getId() == person.getId()) {
                thePerson.setName(person.getName());
                thePerson.setAge(person.getAge());
                thePerson.setEmail(person.getEmail());
                return thePerson;
            }
        }
        throw new PersonNotFoundEx(person.getId());
    }

    @Override
    public boolean deletePerson(int id) throws PersonNotFoundEx {
        for (Person thePerson : personList) {
            if (thePerson.getId() == id) {
                personList.remove(thePerson);
                return true;
            }
        }
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person addPerson(Person person) throws BadRequestEx {
        for (Person thePerson : personList) {
            if (thePerson.getId() == person.getId()) {
                throw new BadRequestEx(person.getId());
            }
        }
        personList.add(person);
        return person;
    }

    @Override
    public int countPersons() {
        return personList.size();
    }
}
