package com.pwr.skirental.repository;

import com.pwr.skirental.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByCategoryId(Long name);
    Integer countByIsBorrowed(boolean borrowed);
    Integer countByIsBorrowedAndCategoryId(boolean borrowed, Long categoryId);

    List<Equipment> findBySize(String size);
}
