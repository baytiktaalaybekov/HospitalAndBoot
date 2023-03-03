package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.entity.Hospital;

import java.util.List;

public interface HospitalSer {
    void saveHospital(Hospital hospital);
    List<Hospital> getAllHospital();

    Hospital findHospitalById(Long id);
    void deleteHospitalById(Long id);

    void updateHospital(Long id,Hospital updateHospital);
}
