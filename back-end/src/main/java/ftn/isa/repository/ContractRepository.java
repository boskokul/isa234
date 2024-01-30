package ftn.isa.repository;

import ftn.isa.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    public Contract findByHospitalName(String hospitalName);
    public List<Contract> findByDayOfMonth(Integer day);
}
