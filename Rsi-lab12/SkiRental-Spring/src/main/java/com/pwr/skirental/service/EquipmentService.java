package com.pwr.skirental.service;

import com.pwr.skirental.entity.Equipment;
import com.pwr.skirental.exception.EntityNotFoundException;
import com.pwr.skirental.exception.InvalidEntityException;
import com.pwr.skirental.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final ModelMapper modelMapper;

    public Collection<Equipment> getEquipments() {
        return equipmentRepository.findAll();
    }


    public Equipment getEquipment(Long id) throws EntityNotFoundException {
        return equipmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    public Equipment addEquipment(Equipment equipment) throws InvalidEntityException {
        return equipmentRepository.save(equipment);
    }


    public Equipment updateEquipment(Equipment equipment) throws EntityNotFoundException, InvalidEntityException {
        Optional<Equipment> equipmentDb = equipmentRepository.findById(equipment.getId());
        if (equipmentDb.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return equipmentRepository.save(equipment);
    }


    public void deleteEquipment(Long id) throws EntityNotFoundException {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        if (equipment.isEmpty()) {
            throw new EntityNotFoundException();
        }
        equipmentRepository.deleteById(id);
    }

    public void changeBorrowStatus(Long id) throws EntityNotFoundException {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        if (equipment.isEmpty()) {
            throw new EntityNotFoundException();
        }
        equipment.get().setBorrowed(!equipment.get().isBorrowed());
        equipmentRepository.save(equipment.get());
    }

    public List<Equipment> getEquipmentsByCategory(Long categoryId) throws EntityNotFoundException {
        return equipmentRepository.findByCategoryId(categoryId);
    }

    public Integer countBorrowed() {
        return equipmentRepository.countByIsBorrowed(true);
    }

    public Integer countBorrowed(Long categoryId) {
        return equipmentRepository.countByIsBorrowedAndCategoryId(true, categoryId);
    }
}
