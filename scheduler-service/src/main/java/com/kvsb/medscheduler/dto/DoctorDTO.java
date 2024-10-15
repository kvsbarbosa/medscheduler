package com.kvsb.medscheduler.dto;

import com.kvsb.medscheduler.domain.Appointment;
import com.kvsb.medscheduler.domain.Doctor;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

    private Long id;

    @NotBlank(message = "Missing name")
    private String name;

    @Column(nullable = false)
    private String specialty;

    @NotBlank(message = "Missing CRM")
    @Pattern(regexp = "^[0-9]{4,6}-[A-Z]{2}$", message = "CRM must be in the format 123456-XX, where XX is the state code")
    private String crm;

    @ElementCollection
    private List<LocalDate> availableTimes;

    private List<Appointment> appointments;

    public DoctorDTO(Doctor entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.specialty = entity.getSpecialty();
        this.crm = entity.getCrm();
        this.availableTimes = entity.getAvailableTimes();
        this.appointments = entity.getAppointments();
    }

}
