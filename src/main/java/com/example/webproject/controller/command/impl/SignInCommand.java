package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePass;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SignInCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePass.SIGN_IN_PAGE.getPagePass());
        UserServiceImpl service = new UserServiceImpl();
        String page = PagePass.SIGN_IN_PAGE.getPagePass();

        String userLogin = request.getParameter(RequestParameter.LOGIN);
        String userPassword = request.getParameter(RequestParameter.PASSWORD);

        List<String> invalidData = null;
        try {
            invalidData = service.signInUser(userLogin, userPassword);
            if (invalidData.isEmpty()) {
                page = PagePass.HOME_PAGE.getPagePass(); //todo
                System.out.println("SignIn command");
            } else {
                session.setAttribute(RequestParameter.INCORRECT_DATA, invalidData);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }return page;
    }
}
