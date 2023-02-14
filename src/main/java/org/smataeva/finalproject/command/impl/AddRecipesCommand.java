package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.Command;
import org.smataeva.finalproject.command.PagePath;
import org.smataeva.finalproject.command.RequestAttribute;
import org.smataeva.finalproject.command.Router;
import org.smataeva.finalproject.exception.CommandException;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.dao.DaoProvider;
import org.smataeva.finalproject.model.dao.RecipeDao;

import javax.servlet.http.HttpServletRequest;

public class AddRecipesCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        String medicationId = request.getParameter(RequestAttribute.MEDICATION_ID);
        String userId = request.getParameter(RequestAttribute.USER_ID);
        try {
            DaoProvider serviceProvider = new DaoProvider();
            RecipeDao recipeService = serviceProvider.getRecipeDao();
            recipeService.addRecipe(userId, medicationId);
            router = new Router(Router.RouterType.REDIRECT, PagePath.MEDICATION_LIST_PAGE);
        } catch (DaoException e) {
            router = new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE);
        }
        return router;
    }
}
