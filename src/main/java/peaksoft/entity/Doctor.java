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
@Table(name = "doctors")
@Getter @Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @SequenceGenerator(name = "doctor_id_gen",sequenceName = "doctor_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doctor_id_gen")
    private Long id;
    @Size(min=1,max = 50,message = "FirstName should be between 1 and 50")
    @NotEmpty(message = "FirstName should not be empty!")
    private String fistName;
    @Size(min=2,max = 50,message = "LastName should be between 2 and 50")
    @NotEmpty(message = "LastName should not be empty!")
    private String lastName;
    @NotEmpty(message = "Position should not be empty!")
    private String position;
    @Column(unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private Hospital hospital;


    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Appointment> appointments ;


    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List<Department> departments;

    public void addDepartment(Department department) {
        if (department==null){
            departments=new ArrayList<>();
        }
        departments.add(department);
    }
}
