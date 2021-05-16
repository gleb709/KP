package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Tariff;
import com.example.webproject.model.entity.TariffAccount;
import com.example.webproject.model.service.impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UserTariffInfoCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.USER_TARIFF_INFO.getPagePath());
        session.setAttribute(RequestParameter.INCORRECT_DATA, RequestParameter.SUCCESS);
        TariffServiceImpl service = new TariffServiceImpl();

        String page = PagePath.USER_TARIFF_INFO.getPagePath();
        String login = (String) session.getAttribute(AttributeKey.LOGIN);

        try {
            Optional<TariffAccount> tariffAccount = service.findTariffAccountInfoByLogin(login);
            if (tariffAccount.isPresent()) {
                Optional<Tariff> tariff = service.findTariffInfo(tariffAccount.get().getTariff());
                if(tariff.isPresent()) {
                    request.setAttribute(RequestParameter.TARIFF, tariff.get().getName());
                    request.setAttribute(RequestParameter.TARIFF_PRICE, tariff.get().getPrice());
                    request.setAttribute(RequestParameter.TARIFF_DISCOUNT, tariff.get().getDiscount());
                    request.setAttribute(RequestParameter.TARIFF_INFO, tariff.get().getInfo());
                }
            } else {
                request.setAttribute(RequestParameter.INCORRECT_DATA, RequestParameter.INVALID);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
