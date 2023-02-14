package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.Command;
import org.smataeva.finalproject.command.PagePath;
import org.smataeva.finalproject.command.RequestAttribute;
import org.smataeva.finalproject.command.Router;
import org.smataeva.finalproject.exception.CommandException;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.dao.DaoProvider;
import org.smataeva.finalproject.model.dao.UserDao;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        String username = request.getParameter(RequestAttribute.USERNAME);
        String firstname = request.getParameter(RequestAttribute.FIRST_NAME);
        String lastname = request.getParameter(RequestAttribute.SECOND_NAME);
        String email = request.getParameter(RequestAttribute.EMAIL);
        String password = request.getParameter(RequestAttribute.PASSWORD);
        DaoProvider daoProvider = new DaoProvider();
        UserDao userDao = daoProvider.getUserDao();
        try {
            boolean isSuccessful = userDao.signUp(username, firstname, lastname,email, password);
            if(isSuccessful) {
                router = new Router(Router.RouterType.REDIRECT, PagePath.SIGN_IN_PAGE);
            } else {
                router = new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE);
            }
        } catch (DaoException e) {
            router = new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE);
        }
        return router;
    }
}
