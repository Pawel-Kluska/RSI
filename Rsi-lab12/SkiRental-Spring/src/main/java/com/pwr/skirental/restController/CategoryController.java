package com.pwr.skirental.restController;

import com.pwr.skirental.entity.Category;
import com.pwr.skirental.exception.EntityNotFoundException;
import com.pwr.skirental.exception.InvalidEntityException;
import com.pwr.skirental.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get/categories")
    public ResponseEntity<Collection<Category>> getAuthors() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/get/category/{id}")
    public ResponseEntity<Category> getAuthor(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoryService.getCategories(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/post/category")
    public ResponseEntity<?> addAuthor(@Valid @RequestBody Category category) {
        try {
            return ResponseEntity.ok(categoryService.addCategories(category));
        } catch (InvalidEntityException e) {
            return ResponseEntity.badRequest().body("Invalid entity");
        }
    }

    @PatchMapping("/patch/category")
    public ResponseEntity<?> updateAuthor(@Valid @RequestBody Category category) {
        try {
            return ResponseEntity.ok(categoryService.updateCategories(category));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Invalid entity");
        }
    }

    @DeleteMapping("/delete/category/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        try {
            categoryService.deleteCategories(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (InvalidEntityException e) {
            return ResponseEntity.badRequest().body("Invalid entity");
        }
    }
}
