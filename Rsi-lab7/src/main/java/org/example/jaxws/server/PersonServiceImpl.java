package org.example.jaxws.server;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService(serviceName = "PersonService",
        endpointInterface = "org.example.jaxws.server.PersonService")
public class PersonServiceImpl implements PersonService {
    private final PersonRepository dataRepository = new PersonRepositoryImpl();

    @WebMethod
    public Person getPerson(int id) throws PersonNotFoundEx {
        System.out.println("...called getPerson id=" + id);
        return dataRepository.getPerson(id);
    }

    @WebMethod
    public List<Person> getAllPersons() {
        System.out.println("...called getAllPersons");
        return dataRepository.getAllPersons();
    }

    @WebMethod
    public Person addPerson(int id, String name, int age) throws PersonExistsEx {
        System.out.println("...called addPerson");
        return dataRepository.addPerson(id, name, age);
    }

    @WebMethod
    public boolean deletePerson(int id) throws PersonNotFoundEx {
        System.out.println("...called deletePerson");
        return dataRepository.deletePerson(id);
    }

    @Override
    public Person updatePerson(int id, String name, int age) throws PersonExistsEx, PersonNotFoundEx {
        System.out.println("...called updatePerson");
        return dataRepository.updatePerson(id, name, age);
    }

    @WebMethod
    public int countPersons() {
        System.out.println("...called countPersons");
        return dataRepository.countPersons();
    }
}