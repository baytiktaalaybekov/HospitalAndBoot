package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Hospital;
import peaksoft.service.HospitalSer;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalApi {

    private final HospitalSer hospitalSer;

    @GetMapping
   public String getAllHospital(Model model){
       model.addAttribute( "hospitals",hospitalSer.getAllHospital());
       return "hospital/hospitals";
    }

    @GetMapping("/new")
   public String create (Model model){
        model.addAttribute("newHospital", new Hospital());
        return "hospital/newHospital";
    }
    @PostMapping("/save")
    String save(@ModelAttribute("newHospital")@Valid Hospital hospital){
        hospitalSer.saveHospital(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("{hospitalId}/delete")
    String delete (@PathVariable("hospitalId")Long id){
        hospitalSer.deleteHospitalById(id);
        return "redirect:/hospitals";
    }

    @GetMapping("/{hospitalId}/edit")
    String edit(@PathVariable("hospitalId")Long id,Model model){
        model.addAttribute("hospital",hospitalSer.findHospitalById(id));
        return "hospital/update";
    }
    @PostMapping("{id}/update")
    String update(@ModelAttribute("id")Long id,@ModelAttribute("hospital")Hospital hospital){
        hospitalSer.updateHospital(id,hospital);
        return "redirect:/hospitals" ;
    }


}
