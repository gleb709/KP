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
import java.util.List;

public class DeleteTariffCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.TARIFF_INFO.getPagePath());
        TariffServiceImpl tariffService = new TariffServiceImpl();

        String page = PagePath.TARIFF_INFO.getPagePath();
        String name = request.getParameter(RequestParameter.TARIFF);

        try {
            List<String> incorrectData = tariffService.deleteTariff(name);
            if(incorrectData.isEmpty()) {
                List<Tariff> tariffs = tariffService.findAllTariffs();
                if (!tariffs.isEmpty()) {
                    session.setAttribute(AttributeKey.TARIFF_DATA, tariffs);
                }else{
                    request.setAttribute(RequestParameter.INCORRECT_DATA, RequestParameter.INVALID);
                }
                page = PagePath.TARIFF_LIST.getPagePath();
            }else{
                request.setAttribute(RequestParameter.INCORRECT_DATA, incorrectData);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
