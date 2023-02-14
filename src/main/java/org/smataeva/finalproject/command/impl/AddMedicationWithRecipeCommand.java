package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.*;
import org.smataeva.finalproject.entity.Basket;
import org.smataeva.finalproject.entity.ImporterMedicine;
import org.smataeva.finalproject.entity.Recipe;
import org.smataeva.finalproject.exception.CommandException;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.dao.DaoProvider;
import org.smataeva.finalproject.model.dao.MedicineDao;
import org.smataeva.finalproject.model.dao.RecipeDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddMedicationWithRecipeCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        String medicationId = request.getParameter(RequestAttribute.MEDICATION_ID);
        String medicineId = request.getParameter(RequestAttribute.MEDICINE_ID);
        String userId = request.getParameter(RequestAttribute.USER_ID);
        String amount = request.getParameter(RequestAttribute.AMOUNT);
        try {
            DaoProvider serviceProvider = new DaoProvider();
            MedicineDao medicineService = serviceProvider.getMedicineDao();
            RecipeDao recipeService = serviceProvider.getRecipeDao();
            List<Recipe> recipes = recipeService.getRecipes();
            Recipe recipe = recipes.stream()
                    .filter(el -> el.getMedicine().getId() == Integer.parseInt(medicineId))
                    .findFirst().get();
            List<ImporterMedicine> importerMedicines = medicineService.findAll();
            ImporterMedicine importerMedicine = importerMedicines.stream()
                    .filter(el -> el.getId() == Integer.parseInt(medicationId))
                    .findFirst().get();
            recipeService.updateRecipe(recipe.getId(), recipe.getMax_dose() - importerMedicine.getDose());
            List<Recipe> recipes0 = recipeService.getRecipes();
            request.getSession().setAttribute(SessionKey.RECIPES, recipes0);
            medicineService.addBasketData(userId, medicationId, Integer.parseInt(amount));
            List<Basket> medicineDataList = medicineService.findBasketData(Integer.parseInt(userId));
            request.setAttribute(SessionKey.BASKET_LIST, medicineDataList);
            request.getSession().setAttribute(SessionKey.MEDICATION_DATA_LIST, medicineDataList);
            router = new Router(Router.RouterType.REDIRECT, PagePath.MAIN_PAGE);
        } catch (DaoException e) {
            router = new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE);
        }
        return router;
    }
}