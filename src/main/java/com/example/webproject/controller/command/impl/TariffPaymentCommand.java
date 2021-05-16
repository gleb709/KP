package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Tariff;
import com.example.webproject.model.entity.TariffAccount;
import com.example.webproject.model.service.impl.TariffServiceImpl;
import com.example.webproject.util.DateTransformer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

public class TariffPaymentCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.TARIFF_PAYMENT.getPagePath());
        TariffServiceImpl service = new TariffServiceImpl();

        String page = PagePath.TARIFF_PAYMENT.getPagePath();
        String login = (String)session.getAttribute(AttributeKey.LOGIN);

        try {
            Optional<TariffAccount> lastPayment = service.findTariffAccountInfoByLogin(login);
            if (lastPayment.isPresent()) {
                Optional<Tariff> tariff = service.findTariffInfo(lastPayment.get().getTariff());
                if (tariff.isPresent()) {
                    if (lastPayment.get().getPaidUpDays() > 0) {
                        Date date = new Date();
                        date = DateTransformer.addDays(date, lastPayment.get().getPaidUpDays());
                        request.setAttribute(RequestParameter.PAYMENT, DateTransformer.dateToString(date));
                    } else {
                        request.setAttribute(RequestParameter.PAYMENT, RequestParameter.INVALID);
                    }
                    session.setAttribute(RequestParameter.BALANCE, lastPayment.get().getBalance());
                    session.setAttribute(RequestParameter.TARIFF, lastPayment.get().getTariff());
                    session.setAttribute(RequestParameter.TARIFF_PRICE, tariff.get().getPrice());
                    session.setAttribute(RequestParameter.TARIFF_DISCOUNT, tariff.get().getDiscount());
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
