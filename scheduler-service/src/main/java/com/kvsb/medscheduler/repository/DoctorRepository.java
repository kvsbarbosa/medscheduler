package com.kvsb.medscheduler.repository;

import com.kvsb.medscheduler.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByName(String name);

    Optional<Doctor> findByCrm(String crm);

    Optional<Doctor> findById(Long id);

    void deleteById(Long id);

}
