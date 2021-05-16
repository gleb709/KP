package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Admin;
import com.example.webproject.model.entity.User;
import com.example.webproject.model.service.impl.UserServiceImpl;
import org.w3c.dom.Attr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.ADMIN_LIST.getPagePath());
        session.setAttribute(AttributeKey.CURRENT_TABLE_PAGE, null);
        UserServiceImpl userService = new UserServiceImpl();

        String page = PagePath.ADMIN_LIST.getPagePath();

        try {
            List<Admin> admins = userService.findAllAdmins();
            System.out.println(admins.size());
            if (!admins.isEmpty()) {
                session.setAttribute(AttributeKey.ADMIN_DATA, admins);
            }else{
                request.setAttribute(RequestParameter.SUCCESS, false);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
