package org.smataeva.finalproject.controller.filter;

import org.smataeva.finalproject.command.SessionKey;
import org.smataeva.finalproject.entity.Role;
import org.smataeva.finalproject.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static org.smataeva.finalproject.command.PagePath.*;

@WebFilter(urlPatterns = {"/*"})
public class PageProtectionFilter implements Filter {
    private List<String> notDoctorPages;
    private List<String> notClientPages;
    private List<String> notGuestPages;

    @Override
    public void init(FilterConfig filterConfig) {
        notGuestPages = List.of(BASKET_PAGE, MEDICATION_LIST_PAGE, MEDICATION_PAGE, PROFILE_PAGE, RECIPES_PAGE, RESERVATION_PAGE);
        notDoctorPages = List.of(MEDICATION_LIST_PAGE, MEDICATION_PAGE, BASKET_PAGE, RESERVATION_PAGE);
        notClientPages = List.of(RECIPES_PAGE);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String servletPath = httpServletRequest.getServletPath();
        HttpSession session = httpServletRequest.getSession();
        User doctor = (User) session.getAttribute(SessionKey.USER);
        Role role;
        if (doctor == null) {
            role = Role.GUEST;
            User doctor1 = new User();
            doctor1.setRole(role);
            session.setAttribute(SessionKey.USER, doctor1);
        } else {
            role = doctor.getRole();
        }
        if (role.equals(Role.DOCTOR) && notDoctorPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(MAIN_PAGE).forward(request, response);
        }
        if (role.equals(Role.PATIENT) && notClientPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(MAIN_PAGE).forward(request, response);
        }
        if (role.equals(Role.GUEST) && notGuestPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(MAIN_PAGE).forward(request, response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}