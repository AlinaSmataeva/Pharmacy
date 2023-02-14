package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.*;
import org.smataeva.finalproject.entity.Recipe;
import org.smataeva.finalproject.exception.CommandException;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.dao.DaoProvider;
import org.smataeva.finalproject.model.dao.RecipeDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UpdateRecipeCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        String notes = request.getParameter(RequestAttribute.NOTES);
        String dose = request.getParameter(RequestAttribute.DOSE);
        String recipeId = request.getParameter(RequestAttribute.RECIPE_ID);
        try {
            DaoProvider serviceProvider = new DaoProvider();
            RecipeDao recipeDao = serviceProvider.getRecipeDao();
            recipeDao.updateRecipe(recipeId, notes, Integer.parseInt(dose));
            List<Recipe> recipes = recipeDao.getRecipes();
            request.getSession().setAttribute(SessionKey.RECIPES, recipes);
            router = new Router(Router.RouterType.REDIRECT, PagePath.VISITS_PAGE);
        } catch (DaoException e) {
            router = new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE);
        }
        return router;
    }
}