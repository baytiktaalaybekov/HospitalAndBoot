package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Department;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.repository.AppointmentReposi;
import peaksoft.repository.DepartmentReposi;
import peaksoft.repository.DoctorReposi;
import peaksoft.repository.HospitalReposi;
import peaksoft.service.DoctorSer;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class DoctorSerImpl implements DoctorSer {
    private final DoctorReposi doctorReposi;
    private final HospitalReposi hospitalReposi;
    private final AppointmentReposi appointmentReposi;
    private final DepartmentReposi departmentReposi;
    @Override
    public void saveDoctor(Long hospitalId, Doctor doctor) {
        Hospital hospital = hospitalReposi.findById(hospitalId).orElseThrow(IllegalArgumentException::new);
        hospital.addDoctor(doctor);
        doctor.setHospital(hospital);
        doctorReposi.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctor(Long id) {
        Hospital hospital = hospitalReposi.findById(id).get();
        return new ArrayList<>(hospital.getDoctors());
    }

    @Override
    public Doctor findDoctorById(Long id) {
        return doctorReposi.findById(id).get();
    }

    @Override
    public void deleteDoctorById(Long id) {
        Doctor doctor = doctorReposi.findById(id).get();
        doctorReposi.delete(doctor);
    }

    @Override
    public void assignDoctor(Long departmentId, Long doctorId) {
        Doctor doctor = doctorReposi.findById(doctorId).orElseThrow(IllegalArgumentException::new);
        Department department = departmentReposi.findById(doctorId).orElseThrow(IllegalArgumentException::new);
        doctor.addDepartment(department);
        department.addDoctor(doctor);
        departmentReposi.save(department);
        doctorReposi.save(doctor);
    }

    @Override
    public void update(Long doctorId, Doctor doctor) {
        Doctor doctor1 = doctorReposi.findById(doctorId).get();
        doctor1.setFistName(doctor.getFistName());
        doctor1.setLastName(doctor.getLastName());
        doctor1.setEmail(doctor.getEmail());
        doctor1.setPosition(doctor.getPosition());
        doctorReposi.save(doctor1);
    }

    @Override
    public List<Department> getDepartments(Long doctorId) {
        return doctorReposi.getDepartments(doctorId);
    }
}
