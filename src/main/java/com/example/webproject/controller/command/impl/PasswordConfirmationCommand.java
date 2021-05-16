package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.User;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PasswordConfirmationCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.CHANGE_PASSWORD_CONFIRMATION.getPagePath());
        String page = PagePath.CHANGE_PASSWORD_CONFIRMATION.getPagePath();
        String emailKey = request.getParameter(RequestParameter.CONFIRMATION_KEY);
        if(session.getAttribute(RequestParameter.CONFIRMATION_KEY).equals(emailKey)){
            page = PagePath.CHANGE_FORGOT_PASSWORD.getPagePath();
        }else {
            request.setAttribute(RequestParameter.CONFIRMATION_KEY, RequestParameter.INVALID);
        }
        return page;
    }
}
