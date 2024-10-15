package com.kvsb.medscheduler.controller;

import com.kvsb.medscheduler.dto.DoctorDTO;
import com.kvsb.medscheduler.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorDTO> findById(@PathVariable Long id) {
        DoctorDTO doctorDTO = service.findById(id);
        return ResponseEntity.ok(doctorDTO);
    }

    @GetMapping(value = "/crm/{crm}")
    public ResponseEntity<DoctorDTO> findByCpf(@PathVariable String crm) {
        DoctorDTO doctorDTO = service.findByCrm(crm);
        return ResponseEntity.ok(doctorDTO);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<DoctorDTO> findByName(@PathVariable String name) {
        DoctorDTO doctorDTO = service.findByCrm(name);
        return ResponseEntity.ok(doctorDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DoctorDTO>> findAll(Pageable pageable) {
        Page<DoctorDTO> doctorDTOS = service.findAll(pageable);
        return ResponseEntity.ok(doctorDTOS);
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> insert(@Valid @RequestBody DoctorDTO doctorDTO) {
        doctorDTO = service.insert(doctorDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(doctorDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(doctorDTO);
    }

    @PutMapping
    public ResponseEntity<DoctorDTO> update(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        doctorDTO = service.update(id, doctorDTO);
        return ResponseEntity.ok(doctorDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
