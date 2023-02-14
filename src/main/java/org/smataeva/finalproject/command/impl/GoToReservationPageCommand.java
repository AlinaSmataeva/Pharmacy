package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.*;
import org.smataeva.finalproject.entity.Basket;
import org.smataeva.finalproject.entity.ImporterMedicine;
import org.smataeva.finalproject.entity.Medicine;
import org.smataeva.finalproject.exception.CommandException;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.dao.DaoProvider;
import org.smataeva.finalproject.model.dao.MedicineDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class GoToReservationPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        String userId = request.getParameter(RequestAttribute.USER_ID);
        DaoProvider serviceProvider = new DaoProvider();
        MedicineDao medicineService = serviceProvider.getMedicineDao();
        List<Basket> medicineList;
        try {
            medicineList = medicineService.findBasketData(Integer.parseInt(userId));
            request.setAttribute(SessionKey.BASKET_LIST, medicineList);
            router = new Router(Router.RouterType.FORWARD, PagePath.RESERVATION_PAGE);
        } catch (DaoException e) {
            router = new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE);
        }
        return router;
    }
}
