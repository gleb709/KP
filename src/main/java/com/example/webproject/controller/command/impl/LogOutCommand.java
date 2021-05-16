package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.HOME_PAGE.getPagePath());

        String page = PagePath.HOME_PAGE.getPagePath();
        session.setAttribute(AttributeKey.ACCOUNT_ROLE, AttributeKey.GUEST_ROLE);

        return page;
    }
}
