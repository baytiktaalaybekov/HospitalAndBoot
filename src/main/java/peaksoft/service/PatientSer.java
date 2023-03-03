package peaksoft.service;

import peaksoft.entity.Patient;

import java.util.List;


public interface PatientSer {
    void save(Long hospitalId, Patient patient);
    List<Patient> getAllPatient(Long hospitalId);
    Patient findPatientById(Long id);
    void deletePatientById(Long id);
    void updatePatient(Long id,Patient updatePatient);

}
