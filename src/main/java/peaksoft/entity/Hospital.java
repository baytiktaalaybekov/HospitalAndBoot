package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "hospitals")
@Getter @Setter
@NoArgsConstructor

public class Hospital {
    @Id
    @SequenceGenerator(name = "hospital_id_gen",sequenceName = "hospital_di_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hospital_id_gen")
    private Long id;

    @Size(min=2,max = 50,message="The name should be between 2 and 50 characters!")
    @NotEmpty(message = "Name should not be empty!")
    private String name;
    @NotEmpty(message = "Address should not be empty")
    private String address;
    @Column(length = 10000)
    private String image;

    @OneToMany(mappedBy = "hospital",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Doctor> doctors ;

    @OneToMany(mappedBy = "hospital",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Patient> patients;

    @OneToMany(mappedBy = "hospital",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Department> departments;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Appointment> appointments;


    public void setPatients(Patient patient) {
        if (patients == null) {
            patients = new ArrayList<>();
        } else {
            patients.add(patient);
        }
    }

    public void addDepartment(Department department) {
        if (departments == null) {
            departments = new ArrayList<>();
        }
        departments.add(department);
    }

    public void addAppointment(Appointment appointment1) {
        appointments.add(appointment1);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }
}
