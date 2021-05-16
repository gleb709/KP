package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Tariff;
import com.example.webproject.model.service.impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HomePageCommand implements ActionCommand {
    private static final String DEFAULT_LOCALE = "ru_RU";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.HOME_PAGE);
        session.setAttribute(AttributeKey.CURRENT_TABLE_PAGE, null);

        TariffServiceImpl tariffService = new TariffServiceImpl();

        if(session.getAttribute(AttributeKey.LOCALE) == null){
            session.setAttribute(AttributeKey.LOCALE, DEFAULT_LOCALE);
        }

        try{
            List<Tariff> tariffs = tariffService.findAllTariffs();
            session.setAttribute(AttributeKey.TARIFF_DATA, tariffs);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return PagePath.HOME_PAGE.getPagePath();
    }
}
