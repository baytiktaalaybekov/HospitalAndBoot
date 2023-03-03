package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Getter @Setter
@NoArgsConstructor
public class Department {
    @Id
    @SequenceGenerator(name = "department_id_gen",sequenceName = "department_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "department_id_gen")
    private Long id;
    @Size(min=2,max = 50,message = "Name should be between 2 and 50 characters!")
    private String name;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private Hospital hospital;

    @ManyToMany(mappedBy = "departments",fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
   private List<Doctor> doctors;

    public void addDoctor(Doctor doctor) {
        if (doctors==null){
            doctors=new ArrayList<>();
        }
        doctors.add(doctor);
    }
}
