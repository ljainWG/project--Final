package com.wg.dabms.service;

import java.sql.SQLException;
import java.util.List;

import com.wg.dabms.dao.AppointmentDAO;
import com.wg.dabms.model.Appointment;
import com.wg.dabms.model.enums.AppointmentStatus;

public class AppointmentService {

    private AppointmentDAO appointmentDAO;

    public AppointmentService() {
        this.appointmentDAO = new AppointmentDAO();
    }

    public void createAppointment(Appointment appointment) {
        try {
            appointmentDAO.createAppointment(appointment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> findAppointmentsByUserId(String userId, AppointmentStatus status) {
        try {
            return appointmentDAO.findByUserId(userId, status);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cancelAppointment(String appointmentId) {
    	try {
			appointmentDAO.updateStatus(appointmentId, AppointmentStatus.CANCELED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
