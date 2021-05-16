package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.service.impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CollectionSubscriptionFeeCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.SUBSCRIPTION_FEE.getPagePath());
        TariffServiceImpl service = new TariffServiceImpl();

        String page = PagePath.SUBSCRIPTION_FEE.getPagePath();
        String login = (String)session.getAttribute(AttributeKey.LOGIN);

        try {
            boolean success = true;
            service.collectionSubscriptionFee(login);
            Optional<String> lastSubscriptionFee = service.lastSubscriptionFeeInfo();
            session.setAttribute(AttributeKey.LAST_PAYMENT, lastSubscriptionFee.get());


            if(success){
                request.setAttribute(RequestParameter.SUCCESS, true);
            }else {
                request.setAttribute(RequestParameter.SUCCESS, false);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
