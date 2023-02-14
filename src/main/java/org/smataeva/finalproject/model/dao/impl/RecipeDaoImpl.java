package org.smataeva.finalproject.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.smataeva.finalproject.entity.*;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.connection.ConnectionPool;
import org.smataeva.finalproject.model.dao.RecipeDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDaoImpl implements RecipeDao {
    private static final Logger logger = LogManager.getLogger();
    private static final RecipeDaoImpl instance = new RecipeDaoImpl();
    private static final String ADD_RECIPE= "INSERT INTO recipe (id_patient, id_medicine, recipe_status) VALUES (?, ?, ?)";
    private static final String UPDATE_RECIPE = "UPDATE recipe set recipe_status = ?, notes = ?, max_dose = ? where id = ?";
    private static final String UPDATE_RECIPE_2 = "UPDATE recipe set max_dose = ? where id = ?";
    private static final String FIND_VISITS = """
                                      SELECT visit.id as vis_id, visit_date, p.username,
                                             d.first_name as doctor_fn, d.last_name as doctor_ln,
                                             p.first_name as patient_fn, p.last_name as patient_ln
                                             from visit
                                                  inner join doctor d on visit.id_doctor = d.id
                                                  inner join patient p on visit.id_patient = p.id
    """;
    private static final String GET_RECIPES = """
                                      SELECT recipe.id as recipe_id, p.username, m.id as med_id,
                                             max_dose, notes,
                                             p.first_name as patient_fn, p.last_name as patient_ln,
                                             medicine_name, recipe_status
                                             from recipe
                                                  inner join patient p on recipe.id_patient = p.id
                                                  inner join medicine m on recipe.id_medicine = m.id
    """;

    public static RecipeDaoImpl getInstance(){
        return instance;
    }

    @Override
    public boolean addRecipe(String userId, String medicineId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_RECIPE)) {
            statement.setString(1, userId);
            statement.setString(2, medicineId);
            statement.setBoolean(3, false);
            return (statement.executeUpdate() == 1);
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Can not proceed request: " + ADD_RECIPE);
            throw new DaoException("Cannot proceed request");
        }
    }

    @Override
    public List<Recipe> getRecipes() throws DaoException {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_RECIPES)){
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getInt("recipe_id"));
                recipe.setRecipeStatus(resultSet.getBoolean("recipe_status"));
                recipe.setNotes(resultSet.getString("notes"));
                recipe.setMax_dose(resultSet.getInt("max_dose"));

                User patient = new User();
                patient.setUsername(resultSet.getString("username"));
                patient.setFirstName(resultSet.getString("patient_fn"));
                patient.setLastName(resultSet.getString("patient_ln"));
                recipe.setPatient(patient);

                Medicine medicine = new Medicine();
                medicine.setId(resultSet.getInt("med_id"));
                medicine.setMedicine_name(resultSet.getString("medicine_name"));
                recipe.setMedicine(medicine);

                recipes.add(recipe);
            }
            return recipes;
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Cannot proceed request: " + GET_RECIPES);
            throw new DaoException("Cannot proceed request");
        }
    }

    @Override
    public boolean updateRecipe(String recipeId, String notes, int maxDose) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE)) {
            statement.setBoolean(1, true);
            statement.setString(2, notes);
            statement.setInt(3, maxDose);
            statement.setString(4, recipeId);
            return (statement.executeUpdate() == 1);
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Can not proceed request: " + UPDATE_RECIPE);
            throw new DaoException("Cannot proceed request");
        }
    }

    @Override
    public boolean updateRecipe(int recipeId, int maxDose) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_2)) {
            statement.setInt(1, maxDose);
            statement.setInt(2, recipeId);
            return (statement.executeUpdate() == 1);
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Can not proceed request: " + UPDATE_RECIPE_2);
            throw new DaoException("Cannot proceed request");
        }
    }

    @Override
    public List<Visit> getVisits() throws DaoException {
        List<Visit> visits = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_VISITS)){
            while (resultSet.next()) {
                Visit visit = new Visit();
                visit.setId(resultSet.getInt("vis_id"));
                visit.setVisitDate(resultSet.getDate("visit_date").toLocalDate());

                User patient = new User();
                patient.setFirstName(resultSet.getString("patient_fn"));
                patient.setLastName(resultSet.getString("patient_ln"));
                patient.setUsername(resultSet.getString("username"));
                visit.setPatient(patient);

                User doctor = new User();
                doctor.setFirstName(resultSet.getString("doctor_fn"));
                doctor.setLastName(resultSet.getString("doctor_ln"));
                visit.setDoctor(doctor);
                visits.add(visit);
            }
            return visits;
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Cannot proceed request: " + FIND_VISITS);
            throw new DaoException("Cannot proceed request");
        }
    }
}
