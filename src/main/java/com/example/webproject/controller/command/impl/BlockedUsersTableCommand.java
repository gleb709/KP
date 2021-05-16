package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Account;
import com.example.webproject.model.entity.User;
import com.example.webproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BlockedUsersTableCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_TABLE_PAGE, null);
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.BLOCKED_USER_LIST.getPagePath());

        String page = PagePath.BLOCKED_USER_LIST.getPagePath();
        return page;
    }
}
