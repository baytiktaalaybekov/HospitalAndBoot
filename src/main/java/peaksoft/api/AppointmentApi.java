package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import peaksoft.entity.Appointment;
import peaksoft.service.AppointmentServi;

import peaksoft.service.DepartmentSer;

import peaksoft.service.DoctorSer;

import peaksoft.service.PatientSer;


import java.util.List;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor

public class AppointmentApi {
    private final AppointmentServi appointmentServi;
    private final PatientSer patientSer;
    private final DoctorSer doctorSer;
    private final DepartmentSer departmentSer;

    @GetMapping("/{hospitalId}")
    String getAllAppointments(Model model,@PathVariable("hospitalId")Long hospitalId){
        model.addAttribute("appointments",appointmentServi.getAllAppointment(hospitalId));
        return "appointment/appointments";
    }
    @GetMapping("/new/{hospitalId}")
    String create(@PathVariable("hospitalId")Long hospitalId,Model model) {
        model.addAttribute("newAppointments", new Appointment());
        model.addAttribute("patients", patientSer.getAllPatient(hospitalId));
        model.addAttribute("departments", departmentSer.getAllDepartment(hospitalId));
        model.addAttribute("doctors", doctorSer.getAllDoctor(hospitalId));
        model.addAttribute("hospitalId",hospitalId);
        return "appointment/newAppointment";


    }
    @PostMapping("/save/{hospitalId}")
    String save(@PathVariable("hospitalId")Long hospitalId,@ModelAttribute("newAppointments") Appointment appointment){
        appointmentServi.saveAppointment(hospitalId,appointment);
        return "redirect:/appointments/"+hospitalId;
    }

    @GetMapping("/edit/{appointmentId}")
    public String edit(@PathVariable("appointmentId") Long appointmentId, Model model){
        Appointment appointment = appointmentServi.findAppointmentById(appointmentId);
        model.addAttribute("appointment", appointmentServi.findAppointmentById(appointmentId));
        model.addAttribute("hospitalId", appointment.getDoctor().getHospital().getId());
        return "appointment/updateAppointment";
    }
    @PutMapping("/{hospitalId}/{appointmentId}/update")
    public String update(@ModelAttribute("appointment")Appointment appointment,
                         @PathVariable("appointmentId")Long appointmentId,
                         @PathVariable("hospitalId")Long hospitalId){
        appointmentServi.updateAppointment(appointmentId, appointment);
        return "redirect:/appointments/" + hospitalId;
    }

    @DeleteMapping("/{hospitalId}/{appointmentId}/delete")
    public String deleteDoctor(@PathVariable("appointmentId")Long appointmentId,
                               @PathVariable("hospitalId")Long hospitalId){
        appointmentServi.deleteAppointmentById(appointmentId,hospitalId);
        return"redirect:/appointments/" + hospitalId;
    }




}
