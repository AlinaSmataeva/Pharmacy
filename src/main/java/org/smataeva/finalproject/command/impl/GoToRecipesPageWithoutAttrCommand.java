package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.Command;
import org.smataeva.finalproject.command.PagePath;
import org.smataeva.finalproject.command.RequestAttribute;
import org.smataeva.finalproject.command.Router;
import org.smataeva.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class GoToRecipesPageWithoutAttrCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(Router.RouterType.FORWARD, PagePath.RECIPES_PAGE);
    }
}
