package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Department;

import java.util.List;

@Repository
public interface DepartmentReposi extends JpaRepository<Department,Long> {




    }
