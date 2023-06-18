package com.pwr.skirental.restController;

import com.pwr.skirental.entity.Equipment;
import com.pwr.skirental.exception.EntityNotFoundException;
import com.pwr.skirental.exception.InvalidEntityException;
import com.pwr.skirental.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/get/equipments")
    public ResponseEntity<Collection<Equipment>> getEquipments() {
        return ResponseEntity.ok(equipmentService.getEquipments());
    }

    @GetMapping("/get/equipment/{id}")
    public ResponseEntity<Equipment> getEquipment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(equipmentService.getEquipment(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/post/equipment")
    public ResponseEntity<?> addEquipment(@Valid @RequestBody Equipment equipment) {
        try {
            return ResponseEntity.ok(equipmentService.addEquipment(equipment));
        } catch (InvalidEntityException e) {
            return ResponseEntity.badRequest().body("Invalid entity");
        }
    }

    @PatchMapping("/patch/equipment")
    public ResponseEntity<?> updateEquipment(@Valid @RequestBody Equipment equipment) {
        try {
            return ResponseEntity.ok(equipmentService.updateEquipment(equipment));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Entity not found");
        } catch (InvalidEntityException e) {
            return ResponseEntity.badRequest().body("Invalid entity");
        }
    }

    @DeleteMapping("/delete/equipment/{id}")
    public ResponseEntity<?> deleteEquipment(@PathVariable Long id) {
        try {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/change-borrow/equipment/{id}")
    public ResponseEntity<?> changeBorrowStatus(@PathVariable Long id) {
        try {
            equipmentService.changeBorrowStatus(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/equipments/category/{categoryId}")
    public ResponseEntity<List<Equipment>> getEquipmentsByCategory(@PathVariable Long categoryId) {
        try {
            return ResponseEntity.ok(equipmentService.getEquipmentsByCategory(categoryId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/equipments/size/{size}")
    public ResponseEntity<List<Equipment>> getEquipmentsBySize(@PathVariable String size) {
        return ResponseEntity.ok(equipmentService.getEquipmentsBySize(size));
    }

    @GetMapping("/get/equipments/countBorrowed")
    public ResponseEntity<Integer> getEquipmentsCountBorrowed() {
        return ResponseEntity.ok(equipmentService.countBorrowed());
    }

    @GetMapping("/get/equipments/countBorrowed/category/{categoryId}")
    public ResponseEntity<Integer> getEquipmentsCountBorrowedByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(equipmentService.countBorrowed(categoryId));
    }
}
