package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import peaksoft.entity.Patient;
import peaksoft.enums.Gender;
import peaksoft.service.HospitalSer;
import peaksoft.service.PatientSer;



import java.util.List;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientApi {
    private final PatientSer patientSer;
    private final HospitalSer hospitalSer;

    @GetMapping("/{id}")
    String getAllDepartments(@PathVariable Long id,Model model){
        List<Patient> patients = patientSer.getAllPatient(id);
        model.addAttribute("patients",patients);
        model.addAttribute("hospitalId",id);
        return "patient/patients";
    }
    @GetMapping("/new/{id}")
    String create(Model model, @PathVariable Long id){
        model.addAttribute("newPatient",new Patient());
        model.addAttribute("hospitalId",id);
        model.addAttribute("male", Gender.MALE.name());
        model.addAttribute("female", Gender.FEMALE.name());
        return "patient/newPatient";
    }

    @PostMapping("/save/{id}")
    String save(@ModelAttribute("newPatient")Patient patient,
                  @PathVariable("id") Long id) {
        patientSer.save(id,patient);
        return "redirect:/patients/" + id;
    }

    @GetMapping("/edit/{patientId}")
    String edit(@PathVariable("patientId")Long id, Model model){
        Patient patient = patientSer.findPatientById(id);
        model.addAttribute("patient",patient);
        model.addAttribute("hospitalId",patient.getHospital().getId());
        model.addAttribute("female",Gender.FEMALE);
        model.addAttribute("male",Gender.FEMALE);
        return "patient/updatePatient";
    }


    @PostMapping("/{hospitalId}/{patientId}/updatePatient")
    String update(@ModelAttribute("patient")Patient patient,
                  @PathVariable("patientId")Long patientId,
                  @PathVariable("hospitalId") Long hospitalId){
        patientSer.updatePatient(patientId,patient);
        return "redirect://patients"+hospitalId;
    }

    @GetMapping("/{patientId}/delete")
    public String deletePatient(@PathVariable Long patientId){
        Long hospitalId = patientSer.findPatientById(patientId).getHospital().getId();
        patientSer.deletePatientById(patientId);
        return"redirect://patients" +hospitalId;
    }

}
