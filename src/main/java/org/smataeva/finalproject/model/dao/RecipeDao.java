package org.smataeva.finalproject.model.dao;

import org.smataeva.finalproject.entity.Recipe;
import org.smataeva.finalproject.entity.Visit;
import org.smataeva.finalproject.exception.DaoException;

import java.util.List;

public interface RecipeDao {
    boolean addRecipe(String userId, String medicineId) throws DaoException;
    boolean updateRecipe(String recipeId, String notes, int maxDose) throws DaoException;
    boolean updateRecipe(int recipeId, int maxDose) throws DaoException;
    List<Visit> getVisits() throws DaoException;
    List<Recipe> getRecipes() throws DaoException;
}
