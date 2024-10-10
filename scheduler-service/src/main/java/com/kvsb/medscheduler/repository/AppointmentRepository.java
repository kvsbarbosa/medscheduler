package com.kvsb.medscheduler.repository;

import com.kvsb.medscheduler.domain.Appointment;
import com.kvsb.medscheduler.domain.Doctor;
import com.kvsb.medscheduler.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByDoctor(Doctor doctor);

}
