package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Tariff;
import com.example.webproject.model.service.impl.TariffServiceImpl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class TariffInfoCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.TARIFF_LIST.getPagePath());
        session.setAttribute(RequestParameter.INCORRECT_DATA, RequestParameter.SUCCESS);
        TariffServiceImpl service = new TariffServiceImpl();

        String page = PagePath.TARIFF_LIST.getPagePath();
        String name = request.getParameter(RequestParameter.TARIFF);

        try {
            Optional<Tariff> tariff = service.findTariffInfo(name);
            if (tariff.isPresent()) {
                session.setAttribute(AttributeKey.TARIFF_DATA, tariff.get());

                page = PagePath.TARIFF_INFO.getPagePath();
            } else {
                request.setAttribute(RequestParameter.INCORRECT_DATA, RequestParameter.INVALID);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
