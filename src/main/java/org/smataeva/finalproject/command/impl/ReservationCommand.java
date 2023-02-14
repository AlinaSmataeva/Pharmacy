package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.*;
import org.smataeva.finalproject.entity.Medicine;
import org.smataeva.finalproject.exception.CommandException;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.dao.DaoProvider;
import org.smataeva.finalproject.model.dao.MedicineDao;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ReservationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        String userId = request.getParameter(RequestAttribute.USER_ID);
        DaoProvider serviceProvider = new DaoProvider();
        MedicineDao medicineService = serviceProvider.getMedicineDao();
        try {
            medicineService.deleteByUserId(Integer.parseInt(userId));
            router = new Router(Router.RouterType.REDIRECT, PagePath.MAIN_PAGE);
            request.getSession().setAttribute(SessionKey.MEDICATION_DATA_LIST, new ArrayList<Medicine>());
        } catch (DaoException e) {
            router = new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE);
        }
        return router;
    }
}
