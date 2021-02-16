package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePass;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.User;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationConfirmationCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePass.EMAIL_CONFIRMATION.getPagePass());
        UserServiceImpl service = new UserServiceImpl();
        String page = PagePass.EMAIL_CONFIRMATION.getPagePass();;
        String emailKey = request.getParameter(RequestParameter.CONFIRMATION_KEY);
        if(session.getAttribute(RequestParameter.CONFIRMATION_KEY).equals(emailKey)){
            User user = (User)session.getAttribute(RequestParameter.USER_DATA);
            String encryptedPassword = (String)session.getAttribute(RequestParameter.PASSWORD);
            try {
                service.addUser(user, encryptedPassword);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            page = PagePass.USER_MENU.getPagePass();
        }else {
            session.setAttribute(RequestParameter.CONFIRMATION_KEY, "invalid");
        }
        return page;
    }
}
