package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangePasswordCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.CHANGE_PASSWORD.getPagePath());
        request.setAttribute(RequestParameter.SUCCESS_MESSAGE, RequestParameter.INVALID);
        UserServiceImpl service = new UserServiceImpl();

        String page = PagePath.CHANGE_PASSWORD.getPagePath();
        String login = (String)session.getAttribute(AttributeKey.LOGIN);

        Map<String, String> password = new HashMap<>();
        password.put(RequestParameter.PASSWORD, request.getParameter(RequestParameter.PASSWORD));
        password.put(RequestParameter.REPEAT_PASSWORD, request.getParameter(RequestParameter.REPEAT_PASSWORD));
        password.put(RequestParameter.NEW_PASSWORD, request.getParameter(RequestParameter.NEW_PASSWORD));

        try {
            List<String> invalidData = service.changePassword(login, password);
            if (invalidData.isEmpty()) {
                request.setAttribute(RequestParameter.SUCCESS_MESSAGE, RequestParameter.SUCCESS);
            } else {
                request.setAttribute(RequestParameter.INCORRECT_DATA, invalidData);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
