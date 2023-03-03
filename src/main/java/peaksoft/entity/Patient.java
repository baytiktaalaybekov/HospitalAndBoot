package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.util.List;

@Entity
@Table(name = "patients")
@Getter @Setter
@NoArgsConstructor
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_id_gen",sequenceName = "patient_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patient_id_gen")
    private Long id;
    @Size(min=2, max = 60,message = "FirstName should be between 2 and 60 characters!")
    @NotEmpty(message = "FirstName should not be empty")
    @Column(name = "first_name")
    private String firstName;
    @Size(min=2,max = 50,message = "LastName should be between 2 and 50 characters!")
    @Column(name = "last_name")
    private String lastName;
    @NotEmpty(message = "Phone_number should not be empty")
    @Column(name="phone_number")
    private String phoneNumber;

    @NotNull
    private Gender gender;

    @Column(unique = true)
    private String email;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    private Hospital hospital;
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Appointment> appointments;
}
