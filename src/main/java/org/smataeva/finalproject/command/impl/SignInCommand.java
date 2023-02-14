package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.*;
import org.smataeva.finalproject.entity.*;
import org.smataeva.finalproject.exception.CommandException;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.dao.DaoProvider;
import org.smataeva.finalproject.model.dao.MedicineDao;
import org.smataeva.finalproject.model.dao.RecipeDao;
import org.smataeva.finalproject.model.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class  SignInCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        String password = request.getParameter(RequestAttribute.PASSWORD);
        String username = request.getParameter(RequestAttribute.USERNAME);
        DaoProvider daoProvider = new DaoProvider();
        UserDao userService = daoProvider.getUserDao();
        MedicineDao medicineService = daoProvider.getMedicineDao();
        RecipeDao recipeService = daoProvider.getRecipeDao();
        try {
            Optional<User> optionalUser = userService.patientSignIn(username, password);
            if (optionalUser.get().getId() != 0) {
                User user = optionalUser.get();
                List<Basket> medicineData = medicineService.findBasketData(user.getId());
                request.getSession().setAttribute(SessionKey.MEDICATION_DATA_LIST, medicineData);
                List<Recipe> recipes = recipeService.getRecipes();
                request.getSession().setAttribute(SessionKey.RECIPES, recipes);
                request.getSession().setAttribute(SessionKey.RECIPES_COUNT, recipes.stream().filter(Recipe::isRecipeStatus).count());
                request.getSession().setAttribute(SessionKey.USER, user);
                router = new Router(Router.RouterType.REDIRECT, PagePath.MAIN_PAGE);
            } else {
                optionalUser = userService.doctorSignIn(username, password);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    request.getSession().setAttribute(SessionKey.USER, user);
                    List<Visit> visits = recipeService.getVisits();
                    request.getSession().setAttribute(SessionKey.VISIT, visits);
                    List<Recipe> recipes = recipeService.getRecipes();
                    request.getSession().setAttribute(SessionKey.RECIPES, recipes);
                    router = new Router(Router.RouterType.REDIRECT, PagePath.MAIN_PAGE);
                } else {
                    router = new Router(Router.RouterType.REDIRECT, PagePath.SIGN_IN_PAGE);
                }
            }
        } catch (DaoException e) {
            router = new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE);
        }
        return router;
    }
}
