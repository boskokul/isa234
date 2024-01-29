package ftn.isa.controller;

import ftn.isa.domain.Contract;
import ftn.isa.dto.ContractDTO;
import ftn.isa.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/contracts")
@PreAuthorize("hasRole('SYSTEM_ADMIN')")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<ContractDTO>> getAll(){
        List<ContractDTO> response = new ArrayList<>();
        for (Contract c:
                contractService.getAll()) {
            response.add(new ContractDTO(c));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity updateContract(@RequestBody ContractDTO contract){
        contractService.save(contract);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/test")
    public ResponseEntity test(@RequestBody String contract){
        contractService.saveFromString(contract);
        return new ResponseEntity(HttpStatus.OK);
    }
}
