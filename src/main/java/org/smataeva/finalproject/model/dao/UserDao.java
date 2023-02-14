package org.smataeva.finalproject.model.dao;

import org.smataeva.finalproject.entity.User;
import org.smataeva.finalproject.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    Optional<User> doctorSignIn(String username, String password) throws DaoException;
    Optional<User> patientSignIn(String username, String password) throws DaoException;
    boolean signUp(String username, String firstName, String lastName, String email, String password) throws DaoException;
}
