package peaksoft.api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import peaksoft.entity.Department;
import peaksoft.entity.Doctor;
import peaksoft.service.DepartmentSer;

import peaksoft.service.DoctorSer;

import java.io.IOException;


@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentApi{
    private final DepartmentSer departmentSer;
    private final DoctorSer doctorSer;


    @GetMapping("/{id}")
    public String getAllDepartments(Model model,
                                    @PathVariable("id") Long id ,
                                    @ModelAttribute("doctor") Doctor doctor ){
        model.addAttribute("departments", departmentSer.getAllDepartment(id));
        model.addAttribute("doctors", doctorSer.getAllDoctor(id));
        model.addAttribute("hospitalId",id);
        return "department/departments";
    }

    @PostMapping("/save/{hospitalId}")
    public String save(@ModelAttribute("newDepartment") Department department,
                       @PathVariable Long hospitalId) {
        departmentSer.saveDepartment(hospitalId, department);
        return "redirect:/departments/"+hospitalId;
    }

    @GetMapping("/new/{id}")
    public String create(Model model,
                         @PathVariable("id")Long id) {
        model.addAttribute("newDepartment", new Department());
        model.addAttribute("hospitalId", id);
        return "/department/newDepartment";
    }

    @GetMapping("/edit/{departmentId}")
    public String edit(@PathVariable("departmentId")Long departmentId,
                       Model model){
        Department department = departmentSer.findDepartmentById(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("hospitalId",department.getHospital().getId());
        return "/department/update";
    }

    @PostMapping("/{hospitalId}/{departmentId}/update")
    public String update(@ModelAttribute("department") Department department,
                         @PathVariable("departmentId") Long departmentId,
                         @PathVariable("hospitalId") Long hospitalId){
        departmentSer.updateDepartment(departmentId,department);
        return "redirect:/departments/" + hospitalId;
    }

    @GetMapping("/{hospitalId}/{departmentId}/delete")
    public String deletePatient(@PathVariable("departmentId")Long id,
                                @PathVariable("hospitalId")Long hospitalId){
        departmentSer.deleteDepartmentById(id);
        return "redirect:/departments/" + hospitalId;
    }
    @PostMapping("{hospitalId}/{departmentId}/assignDoctor")
    private String assignDepartment(@PathVariable("hospitalId") Long hospitalId,
                                    @PathVariable("departmentId") Long departmentId,
                                    @ModelAttribute("doctor") Doctor doctor)
            throws IOException {
        System.out.println(doctor);
        Long id = doctor.getId();
        doctorSer.assignDoctor(departmentId, id);
        return "redirect:/departments/" + hospitalId;
    }

}
