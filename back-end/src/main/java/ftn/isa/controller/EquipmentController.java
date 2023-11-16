package ftn.isa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.domain.Equipment;
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

}
