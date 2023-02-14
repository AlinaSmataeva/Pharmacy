package org.smataeva.finalproject.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.smataeva.finalproject.command.*;
import org.smataeva.finalproject.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandString = request.getParameter(RequestAttribute.COMMAND);
        Command command = CommandType.valueOf(commandString.toUpperCase(Locale.ROOT)).getCommand();
        Router router;
        try {
            router = command.execute(request);
            switch (router.getRouterType()) {
                case FORWARD -> request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                case REDIRECT -> response.sendRedirect(request.getContextPath() + router.getPagePath());
                default -> {
                    logger.log(Level.ERROR, "Illegal routing type");
                    response.sendRedirect(PagePath.ERROR_500_PAGE);
                }
            }
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
}