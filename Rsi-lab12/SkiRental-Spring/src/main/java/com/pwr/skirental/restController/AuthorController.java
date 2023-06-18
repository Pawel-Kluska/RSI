package com.pwr.skirental.restController;

import com.pwr.skirental.exception.EntityNotFoundException;
import com.pwr.skirental.service.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorsService authorsService;

    @GetMapping("/get/authors")
    public ResponseEntity<?> getAuthors() {
        try {
            return ResponseEntity.ok(authorsService.getAuthors());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
