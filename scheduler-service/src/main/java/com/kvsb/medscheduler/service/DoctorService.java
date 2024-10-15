package com.kvsb.medscheduler.service;

import com.kvsb.medscheduler.domain.Doctor;
import com.kvsb.medscheduler.dto.DoctorDTO;
import com.kvsb.medscheduler.repository.DoctorRepository;
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
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Transactional
    public DoctorDTO insert(DoctorDTO doctor) {

        Doctor entity = new Doctor();

        copyDtoToEntity(doctor, entity);

        entity = repository.save(entity);
        return new DoctorDTO(entity);

    }

    @Transactional(readOnly = true)
    public DoctorDTO findById(Long id) {
        Doctor doctor = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id not found"));
        return new DoctorDTO(doctor);
    }

    @Transactional(readOnly = true)
    public DoctorDTO findByCrm(String crm) {
        Doctor doctor = repository.findByCrm(crm).orElseThrow(
                () -> new ResourceNotFoundException("Id not found"));
        return new DoctorDTO(doctor);
    }

    @Transactional(readOnly = true)
    public DoctorDTO findByName(String name) {
        Doctor doctor = repository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Id not found"));
        return new DoctorDTO(doctor);
    }

    @Transactional(readOnly = true)
    public Page<DoctorDTO> findAll(Pageable pageable) {

        Page<Doctor> list = repository.findAll(pageable);
        return list.map(x -> new DoctorDTO(x));

    }

    @Transactional
    public DoctorDTO update(Long id, DoctorDTO doctorDTO) {

        try {
            Doctor entity = repository.getReferenceById(id);
            copyDtoToEntity(doctorDTO, entity);
            entity = repository.save(entity);

            return new DoctorDTO(entity);

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

    private void copyDtoToEntity(DoctorDTO doctorDTO, Doctor entity) {
        entity.setName(doctorDTO.getName());
        entity.setSpecialty(doctorDTO.getSpecialty());
        entity.setCrm(doctorDTO.getCrm());
        entity.setAvailableTimes(doctorDTO.getAvailableTimes());
        entity.setAppointments(doctorDTO.getAppointments());
    }

}
