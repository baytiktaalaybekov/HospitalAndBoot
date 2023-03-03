package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Department;
import peaksoft.entity.Hospital;
import peaksoft.repository.DepartmentReposi;
import peaksoft.repository.HospitalReposi;
import peaksoft.service.DepartmentSer;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class DepartmentSerImpl implements DepartmentSer {

    private final DepartmentReposi departmentReposi;
    private final HospitalReposi hospitalReposi;
    @Override
    public void saveDepartment(Long id, Department department) {
        Hospital hospital = hospitalReposi.findById(id).get();
//        for (Department dep : departmentReposi.findById(id)) {
//           if (dep.getName().equalsIgnoreCase(department.getName())) {
//              throw new BadRequestExseption("");
//           } else {
        hospital.addDepartment(department);
        department.setHospital(hospital);
        departmentReposi.save(department);

    }

    @Override
    public List<Department> getAllDepartment(Long id) {
        Hospital hospital = hospitalReposi.findById(id).get();
        return new ArrayList<>(hospital.getDepartments());
    }

    @Override
    public Department findDepartmentById(Long id) {
        return departmentReposi.findById(id).get();
    }

    @Override
    public void deleteDepartmentById(Long id) {
        Department department = departmentReposi.findById(id).get();
//        department.setDoctors(null);
//        department.setHospital(null);
        departmentReposi.delete(department);
    }

    @Override
    public void updateDepartment(Long id, Department department) {
        Department department1 = departmentReposi.findById(id).get();
        department1.setName(department.getName());
        departmentReposi.save(department1);
    }
}
