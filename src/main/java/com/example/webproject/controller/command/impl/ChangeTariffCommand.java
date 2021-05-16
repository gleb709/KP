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

public class ChangeTariffCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.HOME_TARIFF_INFO.getPagePath());
        request.setAttribute(RequestParameter.SUCCESS_MESSAGE, RequestParameter.INVALID);
        UserServiceImpl service = new UserServiceImpl();

        String page = PagePath.HOME_TARIFF_INFO.getPagePath();
        String tariff = request.getParameter(RequestParameter.TARIFF);
        String login = (String)session.getAttribute(AttributeKey.LOGIN);

        try {
            service.changeTariff(tariff, login);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
