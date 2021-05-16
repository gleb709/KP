package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.TariffAccount;
import com.example.webproject.model.entity.User;
import com.example.webproject.model.service.impl.TariffServiceImpl;
import com.example.webproject.model.service.impl.UserServiceImpl;
import com.example.webproject.util.DateTransformer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

public class UserInfoCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.USER_INFO.getPagePath());
        request.setAttribute(RequestParameter.ACCOUNT_ROLE, session.getAttribute(AttributeKey.ACCOUNT_ROLE));
        UserServiceImpl userService = new UserServiceImpl();

        String page = PagePath.USER_INFO.getPagePath();
        String login = (String)session.getAttribute(AttributeKey.LOGIN);

        try {
            Optional<User> user = userService.findUserInfoByLogin(login);
            if (user != null) {
                session.setAttribute(RequestParameter.LOGIN, login);
                session.setAttribute(RequestParameter.FIRST_NAME, user.get().getFirstName());
                session.setAttribute(RequestParameter.LAST_NAME, user.get().getLastName());
                session.setAttribute(RequestParameter.PHONE_NUMBER, user.get().getPhoneNumber());
                session.setAttribute(RequestParameter.EMAIL, user.get().getEmail());

                session.setAttribute(RequestParameter.CONTRACT_NUMBER, user.get().getTariffAccount().getContractNumber());
                session.setAttribute(RequestParameter.BALANCE, user.get().getTariffAccount().getBalance());
                session.setAttribute(RequestParameter.TARIFF, user.get().getTariffAccount().getTariff());
                if(user.get().getTariffAccount().getPaidUpDays() > 0) {
                    Date date = new Date();
                    date = DateTransformer.addDays(date, user.get().getTariffAccount().getPaidUpDays());
                    session.setAttribute(RequestParameter.PAYMENT, DateTransformer.dateToString(date));
                }else{
                    session.setAttribute(RequestParameter.PAYMENT, RequestParameter.INVALID);
                }
            } else {
                session.setAttribute(RequestParameter.BALANCE, RequestParameter.INVALID);
                session.setAttribute(RequestParameter.TARIFF, RequestParameter.INVALID);
                session.setAttribute(RequestParameter.PAYMENT, RequestParameter.INVALID);
                request.setAttribute(RequestParameter.INCORRECT_DATA, RequestParameter.INVALID);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
