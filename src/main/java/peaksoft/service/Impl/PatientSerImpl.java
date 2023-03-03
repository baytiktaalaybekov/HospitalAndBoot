package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Hospital;
import peaksoft.entity.Patient;
import peaksoft.repository.HospitalReposi;
import peaksoft.repository.PatientReposi;
import peaksoft.service.PatientSer;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PatientSerImpl implements PatientSer {
    private final PatientReposi patientReposi;
    private final HospitalReposi hospitalReposi;


    @Override
    public void save(Long hospitalId, Patient patient) {
        Hospital hospital = hospitalReposi.findById(hospitalId).get();
        hospital.addPatient(patient);
        patient.setHospital(hospital);
        patientReposi.save(patient);
    }

    @Override
    public List<Patient> getAllPatient(Long hospitalId) {
       Hospital hospital = hospitalReposi.findById(hospitalId).get();
        return new ArrayList<>(hospital.getPatients());
    }

    @Override
    public Patient findPatientById(Long id) {
         patientReposi.findById(id);
        return null;
    }

    @Override
    public void deletePatientById(Long id) {
        Patient patient = patientReposi.findById(id).get();
        patientReposi.delete(patient);
    }

    @Override
    public void updatePatient(Long id, Patient updatePatient) {
        Patient patient = patientReposi.findById(id).get();
        patient.setFirstName(updatePatient.getFirstName());
        patient.setLastName(updatePatient.getLastName());
        patient.setPhoneNumber(updatePatient.getPhoneNumber());
        patient.setGender(updatePatient.getGender());
        patient.setEmail(updatePatient.getEmail());
    }
}
