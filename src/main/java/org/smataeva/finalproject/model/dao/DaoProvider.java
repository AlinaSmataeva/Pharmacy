package org.smataeva.finalproject.model.dao;

import org.smataeva.finalproject.model.dao.impl.MedicineDaoImpl;
import org.smataeva.finalproject.model.dao.impl.RecipeDaoImpl;
import org.smataeva.finalproject.model.dao.impl.UserDaoImpl;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final MedicineDao medicineDao = MedicineDaoImpl.getInstance();
    private final RecipeDao recipeDao = RecipeDaoImpl.getInstance();

    public static DaoProvider getInstance(){
        return instance;
    }

    public UserDao getUserDao(){
        return userDao;
    }

    public MedicineDao getMedicineDao(){
        return medicineDao;
    }

    public RecipeDao getRecipeDao(){
        return recipeDao;
    }

}
