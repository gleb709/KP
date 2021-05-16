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

public class AddTariffCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.ADD_TARIFF.getPagePath());
        TariffServiceImpl tariffService = new TariffServiceImpl();

        String page = PagePath.ADD_TARIFF.getPagePath();
        Tariff tariff = new Tariff(request.getParameter(RequestParameter.TARIFF), Double.parseDouble(request.getParameter(RequestParameter.TARIFF_PRICE)), request.getParameter(RequestParameter.TARIFF_INFO),
                Double.parseDouble(request.getParameter(RequestParameter.TARIFF_DISCOUNT)), request.getParameter(RequestParameter.IMAGE));

        try {
            List<String> incorrectData = tariffService.addTariff(tariff);
            if(incorrectData.isEmpty()){
                request.setAttribute(RequestParameter.SUCCESS_MESSAGE, true);
            }else{
                request.setAttribute(RequestParameter.INCORRECT_DATA, incorrectData);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
