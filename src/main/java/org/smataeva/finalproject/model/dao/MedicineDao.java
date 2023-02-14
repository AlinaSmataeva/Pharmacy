package org.smataeva.finalproject.model.dao;

import org.smataeva.finalproject.entity.Basket;
import org.smataeva.finalproject.entity.ImporterMedicine;
import org.smataeva.finalproject.entity.Medicine;
import org.smataeva.finalproject.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MedicineDao {
    List<ImporterMedicine> findAll() throws DaoException;
    List<Basket> findBasketData(int userId) throws DaoException;
    void addBasketData(String userId, String medicationId, int amount) throws DaoException;
    Optional<ImporterMedicine> findById(String id) throws DaoException;
    boolean deleteByUserId(int id) throws DaoException;
}
