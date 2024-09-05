package com.wg.dabms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.wg.dabms.model.Prescription;

public class PrescriptionDAO extends GenericDAO<Prescription> {

    @Override
    protected Prescription mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Prescription prescription = new Prescription();
        prescription.setUuid(resultSet.getString("uuid"));
        prescription.setAppointmentId(resultSet.getString("appointment_id"));
        prescription.setDescription(resultSet.getString("description"));
        prescription.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        prescription.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return prescription;
    }

    public boolean createPrescription(Prescription prescription) throws SQLException {
        String query = "INSERT INTO Prescription (uuid, appointment_id, description, created_at, updated_at) "
                     + "VALUES (?, ?, ?, ?, ?)";
        return executeUpdate(query, prescription.getUuid(), prescription.getAppointmentId(), prescription.getDescription(),
                             java.sql.Timestamp.valueOf(prescription.getCreatedAt()),
                             java.sql.Timestamp.valueOf(prescription.getUpdatedAt()));
    }

    public boolean updatePrescription(Prescription prescription) throws SQLException {
        String query = "UPDATE Prescription SET appointment_id = ?, description = ?, updated_at = ? WHERE uuid = ?";
        return executeUpdate(query, prescription.getAppointmentId(), prescription.getDescription(),
                             java.sql.Timestamp.valueOf(prescription.getUpdatedAt()), prescription.getUuid());
    }

    public boolean deletePrescription(String uuid) throws SQLException {
        String query = "DELETE FROM Prescription WHERE uuid = ?";
        return executeDelete(query, uuid);
    }

    public Prescription getPrescriptionById(String uuid) throws SQLException {
        String query = "SELECT * FROM Prescription WHERE uuid = ?";
        return executeGetQuery(query, uuid);
    }

    public List<Prescription> getAllPrescriptions() throws SQLException {
        String query = "SELECT * FROM Prescription";
        return executeGetAllQuery(query);
    }
    
    public Prescription findByAppointmentId(String appointmentId) throws SQLException {
        String query = "SELECT * FROM Prescription WHERE appointment_id = ?";
        return executeGetQuery(query,appointmentId);
    }
}
