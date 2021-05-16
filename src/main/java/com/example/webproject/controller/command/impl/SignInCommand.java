package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Account;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class SignInCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.SIGN_IN_PAGE.getPagePath());
        UserServiceImpl service = new UserServiceImpl();
        String page = PagePath.SIGN_IN_PAGE.getPagePath();

        String userLogin = request.getParameter(RequestParameter.LOGIN);
        String userPassword = request.getParameter(RequestParameter.PASSWORD);

        try {
            List<String> invalidData = service.signInUser(userLogin, userPassword);
            if (invalidData.isEmpty()) {
                Optional<Account> account = service.findAccountInfoByLogin(userLogin);
                session.setAttribute(AttributeKey.LOGIN, userLogin);
                session.setAttribute(AttributeKey.ACCOUNT_ROLE, account.get().getRole());
                session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.HOME_PAGE.getPagePath());
                page = PagePath.HOME_PAGE.getPagePath();
            } else {
                request.setAttribute(RequestParameter.INCORRECT_DATA, invalidData);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
