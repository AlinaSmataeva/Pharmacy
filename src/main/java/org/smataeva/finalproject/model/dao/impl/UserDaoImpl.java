package org.smataeva.finalproject.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.smataeva.finalproject.entity.Role;
import org.smataeva.finalproject.entity.User;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.connection.ConnectionPool;
import org.smataeva.finalproject.model.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.smataeva.finalproject.model.dao.ColumnName.*;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final String PATIENT_SIGN_IN = """
                        SELECT patient.id as id, username, first_name, last_name, email
                               FROM patient
                         WHERE patient.username = ? AND patient.password = ?
                        """;
    private static final String DOCTOR_SIGN_IN = """
                        SELECT doctor.id as id, username, first_name, last_name, email
                               FROM doctor
                         WHERE doctor.username = ? AND doctor.password = ?
                        """;
    private static final String SIGN_UP = """
                       INSERT INTO patient (username, first_name, last_name, email, password)
                                   VALUES (?, ?, ?, ?, ?)
            """;

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> doctorSignIn(String username, String password) throws DaoException {
        User user = new User();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DOCTOR_SIGN_IN)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(USER_ID));
                user.setUsername(resultSet.getString(USER_USERNAME));
                user.setEmail(resultSet.getString(USER_EMAIL));
                user.setFirstName(resultSet.getString(USER_FIRST_NAME));
                user.setLastName(resultSet.getString(USER_LAST_NAME));
                user.setRole(Role.DOCTOR);
            }
            return Optional.of(user);
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Cannot proceed request: " + PATIENT_SIGN_IN);
            throw new DaoException("Cannot proceed request");
        }
    }

    @Override
    public Optional<User> patientSignIn(String username, String password) throws DaoException {
        User user = new User();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(PATIENT_SIGN_IN)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(USER_ID));
                user.setUsername(resultSet.getString(USER_USERNAME));
                user.setEmail(resultSet.getString(USER_EMAIL));
                user.setFirstName(resultSet.getString(USER_FIRST_NAME));
                user.setLastName(resultSet.getString(USER_LAST_NAME));
                user.setRole(Role.PATIENT);
            }
            return Optional.of(user);
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Cannot proceed request: " + PATIENT_SIGN_IN);
            throw new DaoException("Cannot proceed request");
        }
    }

    @Override
    public boolean signUp(String username, String firstName, String lastName, String email, String password) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();//подключаемся к бд
             PreparedStatement statement = connection.prepareStatement(SIGN_UP)) { //посылаем запрос
            statement.setString(1, username);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, email);
            statement.setString(5, password);
            return (statement.executeUpdate() == 1);
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Can not proceed request: " + SIGN_UP);
            throw new DaoException("Cannot proceed request");
        }
    }
}