package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.Command;
import org.smataeva.finalproject.command.PagePath;
import org.smataeva.finalproject.command.Router;
import org.smataeva.finalproject.entity.ImporterMedicine;
import org.smataeva.finalproject.exception.CommandException;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.dao.DaoProvider;
import org.smataeva.finalproject.model.dao.MedicineDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GoToVisitsPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(Router.RouterType.REDIRECT, PagePath.VISITS_PAGE);
    }
}
