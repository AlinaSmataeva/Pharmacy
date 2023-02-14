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
import java.util.Optional;
import java.util.stream.Collectors;

public class AddMedicationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        String medicationId = request.getParameter(RequestAttribute.MEDICATION_ID);
        String userId = request.getParameter(RequestAttribute.USER_ID);
        String amount = request.getParameter(RequestAttribute.AMOUNT);
        try {
            DaoProvider serviceProvider = new DaoProvider();
            MedicineDao medicineService = serviceProvider.getMedicineDao();
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