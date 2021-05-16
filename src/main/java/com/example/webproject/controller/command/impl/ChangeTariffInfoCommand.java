package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Tariff;
import com.example.webproject.model.service.impl.TariffServiceImpl;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeTariffInfoCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.CHANGE_TARIFF_INFO.getPagePath());
        request.setAttribute(RequestParameter.SUCCESS, false);
        TariffServiceImpl service = new TariffServiceImpl();

        String page = PagePath.CHANGE_TARIFF_INFO.getPagePath();
        Tariff tariff = new Tariff(request.getParameter(RequestParameter.TARIFF), Double.parseDouble(request.getParameter(RequestParameter.TARIFF_PRICE)), request.getParameter(RequestParameter.TARIFF_INFO),
                Double.parseDouble(request.getParameter(RequestParameter.TARIFF_DISCOUNT)), request.getParameter(RequestParameter.IMAGE));

        try {
            List<String> invalidData = service.changeTariffInfo(tariff);
            if (invalidData.isEmpty()) {
                request.setAttribute(RequestParameter.SUCCESS, true);
            } else {
                request.setAttribute(RequestParameter.INCORRECT_DATA, invalidData);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
