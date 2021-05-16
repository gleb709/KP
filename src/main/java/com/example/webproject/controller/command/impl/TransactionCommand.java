package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Payment;
import com.example.webproject.model.entity.Tariff;
import com.example.webproject.model.service.impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class TransactionCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.TRANSACTION.getPagePath());
        TariffServiceImpl tariffService = new TariffServiceImpl();

        String page = PagePath.TRANSACTION.getPagePath();
        String login = (String) session.getAttribute(AttributeKey.LOGIN);
        String sum = request.getParameter(RequestParameter.SUM);
        String creditCard = request.getParameter(RequestParameter.CREDIT_CARD_NUMBER);

        try {
            tariffService.addTransaction(login, sum, creditCard);
            request.setAttribute(RequestParameter.SUCCESS_MESSAGE, true);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
