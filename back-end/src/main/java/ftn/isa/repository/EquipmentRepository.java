package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.domain.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer>{

}
