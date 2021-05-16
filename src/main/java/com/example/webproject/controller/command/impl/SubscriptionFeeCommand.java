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
import java.util.Optional;

public class SubscriptionFeeCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.TARIFF_PAYMENT_INFO.getPagePath());
        TariffServiceImpl service = new TariffServiceImpl();

        String page = PagePath.SUBSCRIPTION_FEE.getPagePath();

        try {
            Optional<String> lastPayment = service.lastSubscriptionFeeInfo();
            if (lastPayment.isPresent()) {
                session.setAttribute(AttributeKey.LAST_PAYMENT, lastPayment.get());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
