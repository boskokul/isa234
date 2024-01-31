package ftn.isa.repository;

import ftn.isa.domain.ContractEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractEquipmentRepository extends JpaRepository<ContractEquipment, Integer> {
    @Query("select ce from ContractEquipment ce join fetch ce.contract c where c.id =?1")
    public List<ContractEquipment> findAllByContractId(Integer contractId);

    @Query("select ce from ContractEquipment ce join fetch ce.contract c where c.hospitalName =?1")
    public List<ContractEquipment> findAllByContractHospitalName(String hospitalName);
}
