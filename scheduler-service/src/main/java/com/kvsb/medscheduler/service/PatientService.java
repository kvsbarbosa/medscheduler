package com.kvsb.medscheduler.service;

import com.kvsb.medscheduler.domain.Patient;
import com.kvsb.medscheduler.dto.PatientDTO;
import com.kvsb.medscheduler.repository.PatientRepository;
import com.kvsb.medscheduler.service.exception.DatabaseException;
import com.kvsb.medscheduler.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    @Transactional
    public PatientDTO insert(PatientDTO patient) {

        Patient entity = new Patient();

        copyDtoToEntity(patient, entity);

        entity = repository.save(entity);
        return new PatientDTO(entity);

    }

    @Transactional(readOnly = true)
    public PatientDTO findById(Long id) {

        Patient patient = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id not found"));
        return new PatientDTO(patient);

    }

    public PatientDTO findByCpf(String cpf) {

        Patient patient = repository.findByCpf(cpf).orElseThrow(
                () -> new ResourceNotFoundException("CPF not found"));
        return new PatientDTO(patient);

    }

    public PatientDTO findByEmail(String email) {

        Patient patient = repository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email not found"));
        return new PatientDTO(patient);

    }

    @Transactional(readOnly = true)
    public Page<PatientDTO> findAll(Pageable pageable) {

        Page<Patient> list = repository.findAll(pageable);
        return list.map(x -> new PatientDTO(x));

    }

    @Transactional
    public PatientDTO update(Long id, PatientDTO patient) {

        try {
            Patient entity = repository.getReferenceById(id);
            copyDtoToEntity(patient, entity);
            entity = repository.save(entity);

            return new PatientDTO(entity);

        } catch (EntityNotFoundException exception) {
            throw new ResourceNotFoundException("Id not found");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found");
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Error at referencial integrity");
        }

    }

    public boolean existsByCpf(String cpf) {

        return repository.existsByCpf(cpf);

    }

    private void copyDtoToEntity(PatientDTO patientDTO, Patient entity) {
        entity.setName(patientDTO.getName());
        entity.setEmail(patientDTO.getEmail());
        entity.setCpf(patientDTO.getCpf());
        entity.setPhoneNumber(patientDTO.getPhoneNumber());
        entity.setBirthDate(patientDTO.getBirthDate());
        entity.setAppointments(patientDTO.getAppointments());
    }

}
