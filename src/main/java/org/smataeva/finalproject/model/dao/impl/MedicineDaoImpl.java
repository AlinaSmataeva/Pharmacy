package org.smataeva.finalproject.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.smataeva.finalproject.entity.*;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.connection.ConnectionPool;
import org.smataeva.finalproject.model.dao.MedicineDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicineDaoImpl implements MedicineDao {
    private static final Logger logger = LogManager.getLogger();
    private static final MedicineDaoImpl instance = new MedicineDaoImpl();
    private static final String FIND_BY_ID = """
            SELECT importer_medicine.id as med_id, id_importer, id_medicine, image,
                   dose, cost, imp_name, medicine_name, recipe, m.id as med_id_2
                   FROM importer_medicine
                        inner join importer i on importer_medicine.id_importer = i.id
                        inner join medicine m on importer_medicine.id_medicine = m.id
               where importer_medicine.id = ?""";
    private static final String DELETE_FROM_BASKET = "DELETE FROM basket WHERE id_user = ?";
    private static final String FIND_BY_USER_ID= """
            SELECT id_importer, basket.id_medicine, image, recipe,
                   dose, cost, imp_name, medicine_name, count(basket.id_medicine) as amount
                   from basket
                        INNER JOIN importer_medicine im on basket.id_medicine = im.id
                        inner join importer i on im.id_importer = i.id
                        inner join medicine m on im.id_medicine = m.id
                        WHERE basket.id_user = ?
                    group by basket.id_medicine
            """;
    private static final String ADD_MEDICINE = "INSERT INTO basket (id_user, id_medicine) VALUES  (?, ?)";
    private static final String FIND_ALL = """
            SELECT importer_medicine.id as med_id, id_importer, id_medicine, image,
                   dose, cost, imp_name, medicine_name, recipe, m.id as med_id_2
                   FROM importer_medicine
                        inner join importer i on importer_medicine.id_importer = i.id
                        inner join medicine m on importer_medicine.id_medicine = m.id
            """;

    public static MedicineDaoImpl getInstance(){
        return instance;
    }

    @Override
    public List<ImporterMedicine> findAll() throws DaoException {
        List<ImporterMedicine> medications = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();//подключаемся к бд
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)){
        while (resultSet.next()) {
            ImporterMedicine importerMedicine = new ImporterMedicine();
            importerMedicine.setId(resultSet.getInt("med_id"));
            importerMedicine.setDose(resultSet.getInt("dose"));
            importerMedicine.setCost(resultSet.getInt("cost"));
            importerMedicine.setImageName(resultSet.getString("image"));

            Medicine medicine = new Medicine();
            medicine.setId(resultSet.getInt("med_id_2"));
            medicine.setMedicine_name(resultSet.getString("medicine_name"));
            medicine.setRecipe(resultSet.getBoolean("recipe"));
            importerMedicine.setMedicine(medicine);

            Importer importer = new Importer();
            importer.setImp_name(resultSet.getString("imp_name"));
            importerMedicine.setImporter(importer);

            medications.add(importerMedicine);
            }
            return medications;
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Cannot proceed request: " + FIND_ALL);
            throw new DaoException("Cannot proceed request");
        }
    }

    @Override
    public List<Basket> findBasketData(int userId) throws DaoException {
        List<Basket> basket = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID)){
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Basket basketLine = new Basket();
                ImporterMedicine importerMedicine = new ImporterMedicine();
                importerMedicine.setDose(resultSet.getInt("dose"));
                int cost = resultSet.getInt("cost");
                importerMedicine.setCost(cost);
                importerMedicine.setImageName(resultSet.getString("image"));

                Medicine medicine = new Medicine();
                medicine.setId(resultSet.getInt("id_medicine"));
                medicine.setMedicine_name(resultSet.getString("medicine_name"));
                medicine.setRecipe(resultSet.getBoolean("recipe"));
                importerMedicine.setMedicine(medicine);

                Importer importer = new Importer();
                importer.setId(resultSet.getInt("id_importer"));
                importer.setImp_name(resultSet.getString("imp_name"));
                importerMedicine.setImporter(importer);

                basketLine.setImpMedicine(importerMedicine);
                int amount = resultSet.getInt("amount");
                basketLine.setAmount(amount);
                basketLine.setSumCost(amount * cost);
                basket.add(basketLine);
            }
            return basket;
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Cannot proceed request: " + FIND_BY_USER_ID);
            throw new DaoException("Cannot proceed request");
        }
    }

    @Override
    public void addBasketData(String userId, String medicationId, int amount) throws DaoException {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            while(amount != 0) {
                PreparedStatement statement = connection.prepareStatement(ADD_MEDICINE);
                statement.setString(1, userId);
                statement.setString(2, medicationId);
                statement.executeUpdate();
                amount--;
            }
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Cannot proceed request: " + ADD_MEDICINE);
            throw new DaoException("Cannot proceed request");
        }
    }

    @Override
    public Optional<ImporterMedicine> findById(String id) throws DaoException {
        ImporterMedicine importerMedicine = new ImporterMedicine();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                importerMedicine.setId(resultSet.getInt("med_id"));
                importerMedicine.setDose(resultSet.getInt("dose"));
                importerMedicine.setCost(resultSet.getInt("cost"));
                importerMedicine.setImageName(resultSet.getString("image"));

                Medicine medicine = new Medicine();
                medicine.setId(resultSet.getInt("med_id_2"));
                medicine.setMedicine_name(resultSet.getString("medicine_name"));
                medicine.setRecipe(resultSet.getBoolean("recipe"));
                importerMedicine.setMedicine(medicine);

                Importer importer = new Importer();
                importer.setImp_name(resultSet.getString("imp_name"));
                importerMedicine.setImporter(importer);
            }
            return Optional.of(importerMedicine);
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Cannot proceed request: " + FIND_BY_ID);
            throw new DaoException("Cannot proceed request");
        }
    }

    @Override
    public boolean deleteByUserId(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FROM_BASKET)) {
            statement.setInt(1, id);
            return (statement.executeUpdate() == 1);
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "Cannot proceed request: " + DELETE_FROM_BASKET);
            throw new DaoException("Cannot proceed request");
        }
    }
}
