package com.kvsb.medscheduler.controller;

import com.kvsb.medscheduler.dto.PatientDTO;
import com.kvsb.medscheduler.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable Long id) {
        PatientDTO patientDTO = service.findById(id);
        return ResponseEntity.ok(patientDTO);
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<PatientDTO> findByCpf(@PathVariable String cpf) {
        PatientDTO patientDTO = service.findByCpf(cpf);
        return ResponseEntity.ok(patientDTO);
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<PatientDTO> findByEmail(@PathVariable String email) {
        PatientDTO patientDTO = service.findByEmail(email);
        return ResponseEntity.ok(patientDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PatientDTO>> findAll(Pageable pageable) {
        Page<PatientDTO> patientDTO = service.findAll(pageable);
        return ResponseEntity.ok(patientDTO);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> insert(@Valid @RequestBody PatientDTO patientDTO) {
        patientDTO = service.insert(patientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patientDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(patientDTO);
    }

    @PutMapping
    public ResponseEntity<PatientDTO> update(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        patientDTO = service.update(id, patientDTO);
        return ResponseEntity.ok(patientDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
