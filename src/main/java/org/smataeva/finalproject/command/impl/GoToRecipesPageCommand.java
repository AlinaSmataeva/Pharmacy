package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.*;
import org.smataeva.finalproject.entity.ImporterMedicine;
import org.smataeva.finalproject.entity.Recipe;
import org.smataeva.finalproject.exception.CommandException;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.dao.DaoProvider;
import org.smataeva.finalproject.model.dao.MedicineDao;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class GoToRecipesPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String recipeId = request.getParameter(RequestAttribute.RECIPE_ID);
        request.setAttribute(RequestAttribute.RECIPE_ID, Integer.parseInt(recipeId));
        return new Router(Router.RouterType.FORWARD, PagePath.RECIPES_PAGE);
    }
}
