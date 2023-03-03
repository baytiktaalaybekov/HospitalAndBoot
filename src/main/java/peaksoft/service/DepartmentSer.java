package peaksoft.service;

import peaksoft.entity.Department;

import java.util.List;


public interface DepartmentSer {
    void saveDepartment(Long id, Department department);
    List<Department> getAllDepartment(Long id);
    Department findDepartmentById(Long id);

    void deleteDepartmentById(Long id);
    void updateDepartment(Long id,Department department);


}
