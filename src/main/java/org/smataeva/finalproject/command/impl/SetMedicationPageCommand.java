package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.*;
import org.smataeva.finalproject.entity.ImporterMedicine;
import org.smataeva.finalproject.entity.Medicine;
import org.smataeva.finalproject.exception.CommandException;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.dao.DaoProvider;
import org.smataeva.finalproject.model.dao.MedicineDao;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SetMedicationPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        String medicationId = request.getParameter(RequestAttribute.MEDICATION_ID);
        String isRecipe = request.getParameter(RequestAttribute.IS_RECIPE);
        DaoProvider serviceProvider = new DaoProvider();
        MedicineDao medicineService = serviceProvider.getMedicineDao();
        try {
            Optional<ImporterMedicine> optionalMedicine = medicineService.findById(medicationId);
            optionalMedicine.ifPresent(medicine -> request.setAttribute(SessionKey.MEDICATION, medicine));
            request.setAttribute(SessionKey.IS_RECIPE, Boolean.parseBoolean(isRecipe));
            router = new Router(Router.RouterType.FORWARD, PagePath.MEDICATION_PAGE);
        } catch (DaoException e) {
            router = new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE);
        }
        return router;
    }
}
