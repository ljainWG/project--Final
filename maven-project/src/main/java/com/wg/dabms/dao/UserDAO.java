package com.wg.dabms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wg.dabms.model.User;
import com.wg.dabms.model.enums.Department;
import com.wg.dabms.model.enums.Gender;
import com.wg.dabms.model.enums.Role;

public class UserDAO extends GenericDAO<User> {

    @Override
    protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUuid(resultSet.getString("uuid"));
        user.setUsername(resultSet.getString("username"));
        user.setSalt(resultSet.getString("salt"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setGender(Gender.valueOf(resultSet.getString("gender")));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setDeptartment(Department.valueOf(resultSet.getString("department")));
        user.setPhoneNo(resultSet.getString("phone_no"));
        user.setAddress(resultSet.getString("address"));
        user.setAvgRating(resultSet.getBigDecimal("avg_rating"));
        user.setNoOfReview(resultSet.getBigDecimal("no_of_review"));
        user.setExperience(resultSet.getBigDecimal("experience"));
        user.setDob(resultSet.getDate("dob").toLocalDate());
        user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        user.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return user;
    }

    public boolean registerUser(User user) throws SQLException {
        String query = "INSERT INTO User (uuid, username, salt, password, email, gender, role, department, phone_no, address, avg_rating, no_of_review, experience, dob, created_at, updated_at) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return executeUpdate(query, user.getUuid(), user.getUsername(), user.getSalt(), user.getPassword(),
                             user.getEmail(), user.getGender().name(), user.getRole().name(),
                             user.getDeptartment().name(), user.getPhoneNo(), user.getAddress(),
                             user.getAvgRating(), user.getNoOfReview(), user.getExperience(),
                             java.sql.Date.valueOf(user.getDob()), java.sql.Timestamp.valueOf(user.getCreatedAt()),
                             java.sql.Timestamp.valueOf(user.getUpdatedAt()));
    }

    public boolean updateUser(User user) throws SQLException {
        String query = "UPDATE User SET username = ?, salt = ?, password = ?, email = ?, gender = ?, role = ?, department = ?, phone_no = ?, address = ?, avg_rating = ?, no_of_review = ?, experience = ?, dob = ?, updated_at = ? WHERE uuid = ?";
        return executeUpdate(query, user.getUsername(), user.getSalt(), user.getPassword(), user.getEmail(),
                             user.getGender().name(), user.getRole().name(), user.getDeptartment().name(),
                             user.getPhoneNo(), user.getAddress(), user.getAvgRating(), user.getNoOfReview(),
                             user.getExperience(), java.sql.Date.valueOf(user.getDob()),
                             java.sql.Timestamp.valueOf(user.getUpdatedAt()), user.getUuid());
    }

    public boolean deleteUser(String uuid) throws SQLException {
        String query = "DELETE FROM User WHERE uuid = ?";
        return executeDelete(query, uuid);
    }

    public User getUserById(String uuid) throws SQLException {
        String query = "SELECT * FROM User WHERE uuid = ?";
        return executeGetQuery(query, uuid);
    }

    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM User";
        return executeGetAllQuery(query);
    }
    
    public User findByEmail(String email) throws SQLException {
    	String query = "SELECT * FROM User WHERE email = ?";
    	return executeGetQuery(query, email);
    }
    
    public List<User> findByDepartment(Department department) throws SQLException {
    	String query = "SELECT * FROM User WHERE department = ?";        
        return executeGetAllQuery(query,department.toString());
    }

    public List<User> findByName(String name) throws SQLException {
        String query = "SELECT * FROM User WHERE username = ?";
        return executeGetAllQuery(query, name);
        
    }

    // Optionally, add methods for querying based on other fields, such as email or username
}
