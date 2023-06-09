package com.pwr.skirental.service;

import com.pwr.skirental.entity.Category;
import com.pwr.skirental.exception.EntityNotFoundException;
import com.pwr.skirental.exception.InvalidEntityException;
import com.pwr.skirental.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final EquipmentService equipmentService;

    
    public Collection<Category> getCategories() {
        return categoryRepository.findAll();
    }

    
    public Category getCategories(Long id) throws EntityNotFoundException {
        return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    
    public Category addCategories(Category category) throws InvalidEntityException {
        return categoryRepository.save(category);
    }

    
    public Category updateCategories(Category category) throws EntityNotFoundException {
        Optional<Category> categoryDb = categoryRepository.findById(category.getId());
        if (categoryDb.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return categoryRepository.save(category);
    }

    
    public void deleteCategories(Long id) throws EntityNotFoundException {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new EntityNotFoundException();
        }
        //check if category is used by any equipment
        equipmentService.getEquipments().forEach(equipment -> {
            if (equipment.getCategory().getId().equals(id)) {
                throw new InvalidEntityException();
            }
        });
        categoryRepository.deleteById(id);
    }

}
