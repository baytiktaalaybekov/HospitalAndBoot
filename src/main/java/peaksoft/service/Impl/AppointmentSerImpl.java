package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Appointment;
import peaksoft.entity.Hospital;
import peaksoft.repository.*;
import peaksoft.service.*;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AppointmentSerImpl implements AppointmentServi {
    private final AppointmentReposi appointmentReposi;

    private final PatientReposi patientReposi;

    private final HospitalReposi hospitalReposi;

    private final DoctorReposi doctorReposi;

    private final DepartmentReposi departmentReposi;


    @Override
    public List<Appointment> getAllAppointment(Long id) {
        return  null;


    }

    @Override
    public Appointment saveAppointment(Long id, Appointment appointment) {
        return null;
    }

    @Override
    public Appointment findAppointmentById(Long id) {
        return appointmentReposi.findById(id).get();
    }

    @Override
    public void deleteAppointmentById(Long id, Long hospitalId) {
        Appointment appointment = appointmentReposi.findById(id).get();
        hospitalReposi.deleteById(id);
        appointmentReposi.deleteById(id);
    }

    @Override
    public void updateAppointment(Long id, Appointment appointment) {
        Appointment appointment1 = appointmentReposi.findById(id).get();
        appointment.setDate(appointment.getDate());
        appointmentReposi.save(appointment1);


    }
}
