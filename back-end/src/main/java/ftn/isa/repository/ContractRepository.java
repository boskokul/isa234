package ftn.isa.repository;

import ftn.isa.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    public Contract findByHospitalName(String hospitalName);
}
