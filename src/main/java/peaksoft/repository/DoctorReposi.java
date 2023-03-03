package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Department;
import peaksoft.entity.Doctor;

import java.util.List;

@Repository
public interface DoctorReposi extends JpaRepository<Doctor,Long> {
    @Query("select dep from Doctor doc join doc.departments dep where doc.id = :doctorId")
    List<Department> getDepartments(Long doctorId);
}
