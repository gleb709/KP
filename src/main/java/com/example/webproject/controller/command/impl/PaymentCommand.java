package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.TariffAccount;
import com.example.webproject.model.service.impl.TariffServiceImpl;
import com.example.webproject.util.DateTransformer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PaymentCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.TARIFF_PAYMENT.getPagePath());
        TariffServiceImpl tariffService = new TariffServiceImpl();

        String page = PagePath.TARIFF_PAYMENT.getPagePath();
        String login = (String)session.getAttribute(AttributeKey.LOGIN);
        int dayCount = Integer.parseInt(request.getParameter(RequestParameter.DAY_COUNT));

        try {
            List<String> incorrectData = tariffService.payment(login, dayCount);
            if(incorrectData.isEmpty()){
                request.setAttribute(RequestParameter.SUCCESS_MESSAGE, true);
                Optional<TariffAccount> tariffAccount = tariffService.findTariffAccountInfoByLogin(login);
                if(tariffAccount.isPresent()){
                    Date date = new Date();
                    date = DateTransformer.addDays(date, tariffAccount.get().getPaidUpDays());
                    session.setAttribute(RequestParameter.PAYMENT, DateTransformer.dateToString(date));
                    session.setAttribute(RequestParameter.BALANCE, tariffAccount.get().getBalance());
                } else {
                    request.setAttribute(RequestParameter.PAYMENT, RequestParameter.INVALID);
                }
            }else{
                request.setAttribute(RequestParameter.INCORRECT_DATA, incorrectData);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
