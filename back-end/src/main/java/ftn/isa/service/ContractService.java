package ftn.isa.service;

import ftn.isa.domain.Contract;
import ftn.isa.domain.ContractEquipment;
import ftn.isa.dto.ContractDTO;
import ftn.isa.dto.ContractEquipmentDTO;
import ftn.isa.repository.ContractEquipmentRepository;
import ftn.isa.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractEquipmentRepository contractEquipmentRepository;

    public Contract findByHospitalName(String HospitalName){
        return contractRepository.findByHospitalName(HospitalName);
    }

    public List<Contract> getAll(){
        return contractRepository.findAll();
    }

    public void save(ContractDTO contract){
        Contract newContract = new Contract(
                contract.getHospitalName(),
                contract.getDuration(),
                contract.getDayOfMonth(),
                contract.getHours(),
                contract.getMinutes(),
                contract.getStatus());
        if(contractRepository.findByHospitalName(contract.getHospitalName()) != null){
            newContract.setId(contractRepository.findByHospitalName(contract.getHospitalName()).getId());
        }
        contractRepository.save(newContract);
        for (ContractEquipment ce:
             contractEquipmentRepository.findAllByContractHospitalName(contract.getHospitalName())) {
            contractEquipmentRepository.delete(ce);
        }
        for (ContractEquipmentDTO ceDTO:
             contract.getContractEquipment()) {
            ContractEquipment ce = new ContractEquipment(ceDTO.getName(), ceDTO.getQuantity());
            ce.setContract(newContract);
            contractEquipmentRepository.save(ce);
        }
    }

    public void saveFromString(String content){
        Contract newContract = new Contract(content);
        System.out.println(newContract.toString());
        if(contractRepository.findByHospitalName(newContract.getHospitalName()) != null){
            newContract.setId(contractRepository.findByHospitalName(newContract.getHospitalName()).getId());
        }
        contractRepository.save(newContract);
        for (ContractEquipment ce:
                contractEquipmentRepository.findAllByContractHospitalName(newContract.getHospitalName())) {
            contractEquipmentRepository.delete(ce);
        }
        for (ContractEquipment ceDTO:
                newContract.getContractEquipment()) {
            ContractEquipment ce = new ContractEquipment(ceDTO.getName(), ceDTO.getQuantity());
            ce.setContract(newContract);
            contractEquipmentRepository.save(ce);
        }
    }
}
