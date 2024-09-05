package com.wg.dabms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.dabms.util.dbConfig.DBConnection;

public abstract class GenericDAO<T> {

    protected Connection connection;

    public GenericDAO() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    public T executeGetQuery(String query, Object... params) throws SQLException {
        T entity = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        }
        return entity;
    }

    public List<T> executeGetAllQuery(String query, Object... params) throws SQLException {
        List<T> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
        }
        return entities;
    }

    public boolean executeUpdate(String query, Object... params) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, params);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean executeDelete(String query, Object... params) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, params);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    private void setParameters(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }
}
