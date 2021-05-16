package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.User;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class UserListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.USER_LIST.getPagePath());
        session.setAttribute(AttributeKey.CURRENT_TABLE_PAGE, null);
        UserServiceImpl userService = new UserServiceImpl();

        String page = PagePath.USER_LIST.getPagePath();

        try {
            List<User> users = userService.findAllUsers();
            if (!users.isEmpty()) {
                session.setAttribute(AttributeKey.USER_DATA, users);
            }else{
                request.setAttribute(RequestParameter.INCORRECT_DATA, RequestParameter.INVALID);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
