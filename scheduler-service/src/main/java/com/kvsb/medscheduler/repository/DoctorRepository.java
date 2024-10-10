package com.kvsb.medscheduler.repository;

import com.kvsb.medscheduler.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByName(String name);

    List<Doctor> findBySpecialty(String specialty);

    Optional<Doctor> findById(Long id);

    List<Doctor> findByNameContaining(String name);

    void deleteById(Long id);

}
