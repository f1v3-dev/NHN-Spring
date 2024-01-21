package com.nhnacademy.certificate.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "resident")
public class Resident {

    @Id
    @Column(name = "resident_serial_number")
    private Integer residentSerialNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber;

    @Column(name = "gender_code")
    private String genderCode;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code")
    private String birthPlaceCode;

    @Column(name = "registration_base_address")
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code")
    private String deathPlaceCode;

    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<HouseholdCompositionResident> householdCompositionResidents;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<BirthDeathReportResident> birthDeathReportResidents;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<CertificateIssue> certificateIssues;

    @OneToMany(mappedBy = "resident", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private List<FamilyRelationship> familyRelationships;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<Household> households;
}
