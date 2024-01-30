package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import ftn.isa.domain.Equipment;

import javax.persistence.LockModeType;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer>{
	
	@Query("select e from Equipment e join fetch e.company c where e.id =?1")
	public Equipment findOneWithCompanies(Integer equipmentId);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select e from Equipment e where e.id =?1")
	public Equipment findByIdLocked(Integer equipmentId);
}
