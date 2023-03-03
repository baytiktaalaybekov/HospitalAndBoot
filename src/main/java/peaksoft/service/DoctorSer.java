package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.entity.Department;
import peaksoft.entity.Doctor;

import java.util.List;


public interface DoctorSer {
    void saveDoctor(Long hospitalId, Doctor doctor);
    List<Doctor> getAllDoctor(Long id);
    Doctor findDoctorById(Long id);
    void deleteDoctorById(Long id);
    void assignDoctor (Long departmentId, Long doctorId);
    void update(Long doctorId,Doctor doctor);

    List<Department> getDepartments(Long doctorId);
}
