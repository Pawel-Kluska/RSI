package org.example.jaxws.client;

import com.example.MyData;
import org.example.jaxws.server_topdown.*;

import java.util.List;
import java.util.Scanner;


public class ESClient {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        MyData.info();
        PersonService_Service pService = new PersonService_Service();
        PersonService pServiceProxy = pService.getPersonServiceImplPort();

        boolean isRunning = true;

        while (isRunning) {
            try {

                System.out.println("1. Pobierz wszsytkie osoby");
                System.out.println("2. Pobierz osobę po id");
                System.out.println("3. Podaj liczbę osób");
                System.out.println("4. Dodaj osobę");
                System.out.println("5. Edytuj dane osobowe");
                System.out.println("6. Usuń osobę");
                System.out.println("7. Wyjście");

                System.out.println("Wybierz operację ");
                int option = scan.nextInt();

                switch (option) {
                    case 1 -> {
                        List<Person> allPersons = pServiceProxy.getAllPersons();
                        allPersons.forEach(ESClient::printPerson);
                    }
                    case 2 -> {
                        System.out.println("Podaj id");
                        Person person = pServiceProxy.getPerson(scan.nextInt());
                        printPerson(person);
                    }
                    case 3 -> System.out.println(pServiceProxy.countPersons());
                    case 4 -> {
                        Object[] personParameters = getPersonParameters();
                        pServiceProxy.addPerson((int) personParameters[0], (String) personParameters[1], (int) personParameters[2]);
                        System.out.println("Zapisano");
                    }
                    case 5 -> {
                        Object[] personParameters = getPersonParameters();
                        pServiceProxy.updatePerson((int) personParameters[0], (String) personParameters[1], (int) personParameters[2]);
                        System.out.println("Zaaktualizowano");
                    }
                    case 6 -> {
                        System.out.println("Podaj id");
                        int id3 = scan.nextInt();
                        pServiceProxy.deletePerson(id3);
                        System.out.println("Usunięto");
                    }
                    case 7 -> isRunning = false;
                    default -> System.out.println("Wprowadzono błędną opcję");
                }
            } catch (PersonNotFoundEx_Exception e) {
                System.out.println("Nie znaleziono użytkownika");
            } catch (PersonExistsEx_Exception e) {
                System.out.println("Taki użytkownik już istnieje");
            }
        }
    }

    private static void printPerson(Person person) {
        System.out.println("Person{" +
                "id=" + person.getId() +
                ", firstName='" + person.getFirstName() + '\'' +
                ", age=" + person.getAge() +
                '}');
    }

    private static Object[] getPersonParameters() {
        Object[] parameters = new Object[3];
        System.out.println("Podaj id");
        parameters[0] = scan.nextInt();
        scan.nextLine();
        System.out.println("Podaj imię");
        parameters[1] = scan.nextLine();
        System.out.println("Podaj wiek");
        parameters[2] = scan.nextInt();
        return parameters;
    }
}