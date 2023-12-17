package ftn.isa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.domain.Company;
import ftn.isa.domain.Equipment;
import ftn.isa.domain.Student;
import ftn.isa.dto.CompanyResponseDTO;
import ftn.isa.dto.EquipmentCreateDTO;
import ftn.isa.dto.EquipmentDTO;
import ftn.isa.dto.StudentDTO;
import ftn.isa.service.CompanyService;
import ftn.isa.service.EquipmentService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/equipment")
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private CompanyService companyService;
	@GetMapping
    public ResponseEntity<List<EquipmentDTO>> getEquipment() {
        List<Equipment> equipment = equipmentService.findAll();

        List<EquipmentDTO> equipmentDTOs = new ArrayList<>();
        for (Equipment e : equipment) {
        	equipmentDTOs.add(new EquipmentDTO(e));
        }

        return new ResponseEntity<>(equipmentDTOs, HttpStatus.OK);
    }
	@GetMapping(value = "/{equipmentId}/companies")
	public ResponseEntity<List<CompanyResponseDTO>> getEquipmentCompanies(@PathVariable Integer equipmentId) {

		Equipment equipment = equipmentService.findOneWithCompanies(equipmentId);

		Set<Company> companies = new HashSet<>();
		companies.add(equipment.getCompany());
		List<CompanyResponseDTO> companyDTOs = new ArrayList<>();

		for (Company c : companies) {
			companyDTOs.add(new CompanyResponseDTO(c));
		}
		return new ResponseEntity<>(companyDTOs, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Integer id) {

        Equipment e = equipmentService.getById(id);

        if (e != null) {
        	equipmentService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PostMapping(consumes = "application/json")
    public ResponseEntity<EquipmentDTO> saveStudent(@RequestBody EquipmentCreateDTO eDTO) {

        Equipment e = new Equipment();
        e.setDescription(eDTO.getDescription());
        e.setFreeAmount(eDTO.getAmount());
        e.setReservedAmount(0);
        e.setName(eDTO.getName());
        e.setType(eDTO.getType());
        
        e.setCompany(companyService.getById(eDTO.getCompanyId()));

        e = equipmentService.save(e);
        return new ResponseEntity<>(new EquipmentDTO(e), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<EquipmentDTO> updateStudent(@RequestBody EquipmentDTO eDTO) {

        // a student must exist
        Equipment e = equipmentService.findOneWithCompanies(eDTO.getId());

        if (e == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        e.setDescription(eDTO.getDescription());
        e.setFreeAmount(eDTO.getFreeAmount());
        e.setName(eDTO.getName());
        e.setType(eDTO.getType());

        e = equipmentService.save(e);
        return new ResponseEntity<>(new EquipmentDTO(e), HttpStatus.OK);
    }
}
