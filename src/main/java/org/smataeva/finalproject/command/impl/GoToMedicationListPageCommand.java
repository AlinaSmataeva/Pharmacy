package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.Command;
import org.smataeva.finalproject.command.PagePath;
import org.smataeva.finalproject.command.Router;
import org.smataeva.finalproject.command.SessionKey;
import org.smataeva.finalproject.entity.ImporterMedicine;
import org.smataeva.finalproject.exception.CommandException;
import org.smataeva.finalproject.exception.DaoException;
import org.smataeva.finalproject.model.dao.DaoProvider;
import org.smataeva.finalproject.model.dao.MedicineDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GoToMedicationListPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        DaoProvider serviceProvider = new DaoProvider();
        MedicineDao medicineService = serviceProvider.getMedicineDao();
        List<ImporterMedicine> medicineList;
        try {
            medicineList = medicineService.findAll();
            request.setAttribute(SessionKey.MEDICINE_LIST, medicineList);
            router = new Router(Router.RouterType.FORWARD, PagePath.MEDICATION_LIST_PAGE);
        } catch (DaoException e) {
            router = new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE);
        }
        return router;
    }
}
