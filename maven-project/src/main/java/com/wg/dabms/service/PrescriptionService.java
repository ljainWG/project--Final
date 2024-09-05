package com.wg.dabms.service;

import java.sql.SQLException;

import com.wg.dabms.dao.PrescriptionDAO;
import com.wg.dabms.model.Prescription;

public class PrescriptionService {

    private PrescriptionDAO prescriptionDAO;

    public PrescriptionService() {
        this.prescriptionDAO = new PrescriptionDAO();
    }

    public void createPrescription(Prescription prescription) {
        try {
            prescriptionDAO.createPrescription(prescription);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Prescription findByAppointmentId(String appointmentId) {
        try {
            return prescriptionDAO.findByAppointmentId(appointmentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
