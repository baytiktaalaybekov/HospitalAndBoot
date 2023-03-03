package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.entity.Appointment;

import java.util.List;

public interface AppointmentServi {
    List<Appointment> getAllAppointment(Long id);
    Appointment saveAppointment(Long id,Appointment appointment);

    Appointment findAppointmentById(Long id);

    void deleteAppointmentById(Long id, Long hospitalId);

    void updateAppointment(Long id,Appointment appointment);



}
