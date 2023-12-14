package ftn.isa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.domain.Company;
import ftn.isa.domain.Equipment;
import ftn.isa.dto.CompanyResponseDTO;
import ftn.isa.dto.EquipmentDTO;
import ftn.isa.service.EquipmentService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/equipment")
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;
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
}
