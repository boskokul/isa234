package ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.domain.Equipment;
import ftn.isa.domain.Student;
import ftn.isa.repository.EquipmentRepository;

@Service
public class EquipmentService {
	@Autowired
	EquipmentRepository equipmentRepository;
	
	public List<Equipment> findAll(){
		return equipmentRepository.findAll();
	}
	
	public void remove(Integer id) {
		equipmentRepository.deleteById(id);
    }
	
	public Equipment save(Equipment e) {
        return equipmentRepository.save(e);
    }
	
	public Equipment getById(int id) {
		return equipmentRepository.getReferenceById(id);
	}
	
	public Equipment Save(Equipment e) {
		return equipmentRepository.save(e);
	}
	
	public Equipment findOneWithCompanies(Integer equipmentId) {
		return equipmentRepository.findOneWithCompanies(equipmentId);
	}
}
