package com.wg.dabms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.dabms.dao.UserDAO;
import com.wg.dabms.model.User;
import com.wg.dabms.model.enums.Department;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void createUser(User user) {
        try {
            userDAO.registerUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findByEmail(String email) {
        try {
            return userDAO.findByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> findUserByDepartment(Department department) {
        try {
        	List<User> doctors = userDAO.findByDepartment(department);
            return doctors != null ? doctors : new ArrayList<>();
        } catch (SQLException e) {
        	System.out.println("Empty list");
            e.printStackTrace();
            return null;
        }
    }

    public List<User> findUsersByName(String name) {
        try {
            return userDAO.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> findAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

	public void deleteUser(String uuid) {
		// TODO Auto-generated method stub
		
	}

	public void updateUser(String uuid) {
		// TODO Auto-generated method stub
		
	}
}
