package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Admin;
import com.example.webproject.model.entity.User;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AdminAccountInfoCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.ADMIN_LIST.getPagePath());
        request.setAttribute(RequestParameter.ACCOUNT_ROLE, session.getAttribute(AttributeKey.ACCOUNT_ROLE));
        UserServiceImpl userService = new UserServiceImpl();

        String page = PagePath.ADMIN_LIST.getPagePath();
        String login = request.getParameter(RequestParameter.LOGIN);
        try {
            Optional<Admin> admin = userService.findAdminInfoByLogin(login);
            if (admin.isPresent()) {
                session.setAttribute(RequestParameter.LOGIN, admin.get().getAccount().getLogin());
                session.setAttribute(RequestParameter.FIRST_NAME, admin.get().getFirstName());
                session.setAttribute(RequestParameter.LAST_NAME, admin.get().getLastName());
                session.setAttribute(RequestParameter.PHONE_NUMBER, admin.get().getPhoneNumber());
                session.setAttribute(RequestParameter.EMAIL, admin.get().getEmail());
                page = PagePath.ADMIN_ACCOUNT_INFO.getPagePath();
            }else{
                request.setAttribute(RequestParameter.INCORRECT_DATA, RequestParameter.INVALID);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
