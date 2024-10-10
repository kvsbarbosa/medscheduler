package com.kvsb.medscheduler.repository;

import com.kvsb.medscheduler.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

}
