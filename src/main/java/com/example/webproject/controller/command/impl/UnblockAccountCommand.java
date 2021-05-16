package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnblockAccountCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.USER_ACCOUNT_INFO.getPagePath());
        UserServiceImpl service = new UserServiceImpl();

        String page = PagePath.USER_ACCOUNT_INFO.getPagePath();
        String login = request.getParameter(RequestParameter.LOGIN);

        try {
            service.changeAccountStatus(login, RequestParameter.IS_ACTIVE);
            request.setAttribute(RequestParameter.ACCOUNT_STATUS, RequestParameter.IS_ACTIVE);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
