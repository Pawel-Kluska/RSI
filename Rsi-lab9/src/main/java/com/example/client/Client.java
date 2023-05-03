package com.example.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        Scanner scanner = new Scanner(System.in);
        ObjectMapper objectMapper = new ObjectMapper();

        int option = 0;

        while (option != 6) {
            System.out.println("1. Wyświetl wszystkich");
            System.out.println("2. Wyświetl użytkownika o podanym id");
            System.out.println("3. Dodaj użytkownika");
            System.out.println("4. Zaktualizuj użytkownika");
            System.out.println("5. Usuń użytkownika");
            System.out.println("6. Wyjdź");

            try {

                System.out.println("Wybierz opcję:");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        String jsonString = restTemplate.getForObject("http://localhost:8080/persons", String.class);
                        JsonNode jsonNode = objectMapper.readTree(jsonString);
                        JsonNode rootNode = jsonNode.get("_embedded");
                        if (rootNode == null) {
                            System.out.println("Brak użytkowników");
                            break;
                        }
                        JsonNode personsNode = rootNode.get("personList");
                        List<Person> personList = objectMapper.readValue(personsNode.toString(),
                                new TypeReference<List<Person>>() {
                                });
                        personList.forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("Podaj id użytkownika");
                        int id = scanner.nextInt();
                        Person person = restTemplate.getForObject("http://localhost:8080/persons/" + id, Person.class);
                        System.out.println(person);
                        break;
                    case 3:
                        System.out.println("Podaj id użytkownika");
                        int id2 = scanner.nextInt();
                        System.out.println("Podaj imię");
                        String name = scanner.next();
                        System.out.println("Podaj wiek");
                        int age = scanner.nextInt();
                        System.out.println("Podaj email");
                        String email = scanner.next();
                        Person newPerson = restTemplate
                                .postForObject("http://localhost:8080/persons",
                                        new Person(id2, name, age, email), Person.class);
                        System.out.println(newPerson);
                        break;
                    case 4:
                        System.out.println("Podaj id użytkownika");
                        int id3 = scanner.nextInt();
                        System.out.println("Podaj imię");
                        String name2 = scanner.next();
                        System.out.println("Podaj wiek");
                        int age2 = scanner.nextInt();
                        System.out.println("Podaj email");
                        String email2 = scanner.next();

                        HttpEntity<Person> requestEntity = new HttpEntity<>(new Person(id3, name2, age2, email2));

                        Person updatedPerson = restTemplate.exchange("http://localhost:8080/persons",
                                HttpMethod.PUT,
                                requestEntity,
                                Person.class).getBody();
                        System.out.println(updatedPerson);
                        break;
                    case 5:
                        System.out.println("Podaj id użytkownika");
                        int id4 = scanner.nextInt();
                        restTemplate.exchange("http://localhost:8080/persons/" + id4,
                                HttpMethod.DELETE,
                                null, String.class);

                        System.out.println("Deleted");
                        break;
                    case 6:
                        System.out.println("Wyjdź");
                        break;
                }
            } catch (HttpClientErrorException e) {
                System.out.println("Błąd: " + e.getMessage());
            }
            catch (InputMismatchException e) {
                System.out.println("Wprowadź poprawne dane");
            }
        }
    }
}
