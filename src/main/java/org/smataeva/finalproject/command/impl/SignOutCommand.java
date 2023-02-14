package org.smataeva.finalproject.command.impl;

import org.smataeva.finalproject.command.Command;
import org.smataeva.finalproject.command.PagePath;
import org.smataeva.finalproject.command.Router;
import org.smataeva.finalproject.command.SessionKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements Command {
    @Override
    public Router execute (HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(SessionKey.USER);
        return new Router(Router.RouterType.REDIRECT, PagePath.MAIN_PAGE);
    }
}
