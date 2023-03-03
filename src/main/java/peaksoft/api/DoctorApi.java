package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import peaksoft.entity.Doctor;

import peaksoft.service.DepartmentSer;
import peaksoft.service.DoctorSer;
import peaksoft.service.HospitalSer;

import java.io.IOException;


//
@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorApi {
    private final DoctorSer doctorSer;
    private final HospitalSer hospitalSer;


    @GetMapping("/{hospitalId}")
    public String getAllDoctor(Model model, @PathVariable Long hospitalId) {
        model.addAttribute("doctors", doctorSer.getAllDoctor(hospitalId));
        model.addAttribute("hospitalId", hospitalId);
        return "/doctor/doctors";
    }


    @GetMapping("/new/{id}")
    public String create(Model model, @PathVariable Long id) {
        model.addAttribute("newDoctor", new Doctor());
        model.addAttribute("hospitalId", id);
        return "/doctor/newDoctor";
    }

    @PostMapping("/save/{id}")
    public String saveDoctor(@ModelAttribute("newDoctor") Doctor doctor,
                             @PathVariable("id") Long id) {
        doctorSer.saveDoctor(id, doctor);
        return "redirect:/doctors/" + id;
    }


    @GetMapping("/edit/{doctorId}")
    public String edit(@PathVariable("doctorId") Long doctorId,
                       Model model) {
        Doctor doctor = doctorSer.findDoctorById(doctorId);
        model.addAttribute("doctor", doctor);
        model.addAttribute("hospitalId", doctor.getHospital().getId());
        return "/doctor/updateDoctor";
    }

    @PostMapping("/{hospitalId}/{doctorId}/update")
    public String update(@ModelAttribute("doctor") Doctor doctor,
                         @PathVariable("doctorId") Long doctorId,
                         @PathVariable("hospitalId") Long hospitalId) {
        doctorSer.update(doctorId, doctor);
        return "redirect:/doctors/" + hospitalId;
    }

    @GetMapping("/{hospitalId}/{doctorId}/delete/")
    String delete(@PathVariable Long hospitalId,
                  @PathVariable Long doctorId) {
        doctorSer.deleteDoctorById(doctorId);
        return "redirect: /doctors/" + hospitalId;
    }
}