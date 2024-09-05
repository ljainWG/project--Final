package com.wg.dabms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.wg.dabms.model.Appointment;
import com.wg.dabms.model.enums.AppointmentStatus;
import com.wg.dabms.model.enums.TimeSlot;

public class AppointmentDAO extends GenericDAO<Appointment> {

	@Override
	protected Appointment mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Appointment appointment = new Appointment();
		appointment.setUuid(resultSet.getString("uuid"));
		appointment.setDoctorId(resultSet.getString("doctor_id"));
		appointment.setPatientId(resultSet.getString("patient_id"));
		appointment.setStatus(AppointmentStatus.valueOf(resultSet.getString("status")));
		appointment.setScheduledDate(resultSet.getDate("scheduled_date").toLocalDate());
		appointment.setSlotNo(TimeSlot.valueOf(resultSet.getString("slot_no")));
		appointment.setBookingDate(resultSet.getTimestamp("booking_date").toLocalDateTime());
		appointment.setStatusUpdationDate(resultSet.getTimestamp("status_updation_date").toLocalDateTime());
		return appointment;
	}

	public boolean createAppointment(Appointment appointment) throws SQLException {
		String query = "INSERT INTO Appointment (uuid, doctor_id, patient_id, status, scheduled_date, slot_no, booking_date, status_updation_date) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		return executeUpdate(query, appointment.getUuid(), appointment.getDoctorId(), appointment.getPatientId(),
				appointment.getStatus().name(), java.sql.Date.valueOf(appointment.getScheduledDate()),
				appointment.getSlotNo().name(), java.sql.Timestamp.valueOf(appointment.getBookingDate()),
				java.sql.Timestamp.valueOf(appointment.getStatusUpdationDate()));
	}

	public boolean updateAppointment(Appointment appointment) throws SQLException {
		String query = "UPDATE Appointment SET doctor_id = ?, patient_id = ?, status = ?, scheduled_date = ?, slot_no = ?, booking_date = ?, status_updation_date = ? WHERE uuid = ?";
		return executeUpdate(query, appointment.getDoctorId(), appointment.getPatientId(),
				appointment.getStatus().name(), java.sql.Date.valueOf(appointment.getScheduledDate()),
				appointment.getSlotNo().name(), java.sql.Timestamp.valueOf(appointment.getBookingDate()),
				java.sql.Timestamp.valueOf(appointment.getStatusUpdationDate()), appointment.getUuid());
	}

	public boolean deleteAppointment(String uuid) throws SQLException {
		String query = "DELETE FROM Appointment WHERE uuid = ?";
		return executeDelete(query, uuid);
	}

	public Appointment getAppointmentById(String uuid) throws SQLException {
		String query = "SELECT * FROM Appointment WHERE uuid = ?";
		return executeGetQuery(query, uuid);
	}

	public List<Appointment> getAllAppointments() throws SQLException {
		String query = "SELECT * FROM Appointment";
		return executeGetAllQuery(query);
	}

	public List<Appointment> findByUserId(String userId, AppointmentStatus status) throws SQLException {
		String query = "SELECT * FROM Appointment WHERE (doctor_id = ? OR patient_id = ?) AND status = ?";
		return executeGetAllQuery(query, userId, status);
	}

	public boolean updateStatus(String appointmentId, AppointmentStatus newstatus) throws SQLException {
		String query = "SELECT * FROM Appointment WHERE (uuid = ? OR patient_id = ?) AND status = ?";
		return executeUpdate(query, appointmentId, newstatus);		
	}

}
