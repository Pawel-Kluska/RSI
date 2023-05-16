package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthorController {

    @GetMapping("/authors")
    public Map<String, String> getAuthors() {
        return Map.of(
                "author1", "Paweł Kluska",
                "author2", "Katya Zyatikava"
        );

    }
}
