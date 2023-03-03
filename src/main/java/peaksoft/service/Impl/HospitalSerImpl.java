package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Hospital;
import peaksoft.repository.HospitalReposi;
import peaksoft.service.HospitalSer;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class HospitalSerImpl implements HospitalSer {

    private final HospitalReposi hospitalReposi;
    @Override
    public void saveHospital(Hospital hospital) {
        hospitalReposi.save(hospital);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalReposi.findAll();
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalReposi.findById(id).get();
    }

    @Override
    public void deleteHospitalById(Long id) {
        hospitalReposi.deleteById(id);
    }

    @Override
    public void updateHospital(Long id, Hospital updateHospital) {
        Hospital hospital = hospitalReposi.findById( id).get();
        hospital.setName(updateHospital.getName());
        hospital.setAddress(updateHospital.getAddress());
        hospital.setImage(updateHospital.getImage());
        hospitalReposi.save(hospital);
    }
}
