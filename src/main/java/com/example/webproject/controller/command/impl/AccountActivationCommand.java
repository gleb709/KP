package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AccountActivationCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserServiceImpl service = new UserServiceImpl();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.ACCOUNT_ACTIVATION.getPagePath());
        String page = PagePath.ACCOUNT_ACTIVATION.getPagePath();

        String login = request.getParameter(RequestParameter.LOGIN);
        String emailKey = request.getParameter(RequestParameter.CONFIRMATION_KEY);

        try {
            List<String> invalidData = service.accountActivation(login, emailKey);
            if(invalidData.isEmpty()){
                session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.SIGN_IN_PAGE.getPagePath());
                page = PagePath.SIGN_IN_PAGE.getPagePath();
            }else{
                request.setAttribute(RequestParameter.INCORRECT_DATA, invalidData);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
