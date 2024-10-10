package com.kvsb.medscheduler.dto;

import com.kvsb.medscheduler.domain.Appointment;
import com.kvsb.medscheduler.domain.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class PatientDTO {

    private Long id;

    @NotBlank(message = "Missing name")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Missing CPF")
    @Size(min = 11, max = 11, message = "CPF must have 11 digits")
    private String cpf;

    @NotBlank(message = "Missing phone number")
    @Pattern(regexp = "\\d{10,11}", message = "Phone number must contain 10 or 11 digits")
    private String phoneNumber;

    @NotBlank(message = "Missing birth date")
    private LocalDate birthDate;

    private List<Appointment> appointments;

    public PatientDTO(Patient entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.cpf = entity.getCpf();
        this.phoneNumber = entity.getPhoneNumber();
        this.birthDate = entity.getBirthDate();
        this.appointments = entity.getAppointments();
    }

}
