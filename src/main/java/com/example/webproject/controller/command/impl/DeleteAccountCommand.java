package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Admin;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteAccountCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
         HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.TARIFF_INFO.getPagePath());
        UserServiceImpl userService = new UserServiceImpl();

        String page = PagePath.ADMIN_INFO.getPagePath();
        String login = request.getParameter(RequestParameter.LOGIN);

        try {
            List<String> incorrectData = userService.deleteAccount(login);
            if(incorrectData.isEmpty()) {
                List<Admin> admins = userService.findAllAdmins();
                if (!admins.isEmpty()) {
                    session.setAttribute(AttributeKey.ADMIN_DATA, admins);
                }else{
                    request.setAttribute(RequestParameter.INCORRECT_DATA, RequestParameter.INVALID);
                }
                page = PagePath.ADMIN_LIST.getPagePath();
            }else{
                request.setAttribute(RequestParameter.INCORRECT_DATA, incorrectData);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
