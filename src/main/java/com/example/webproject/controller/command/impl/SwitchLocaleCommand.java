package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SwitchLocaleCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String locale = request.getParameter(RequestParameter.LOCALE);
        session.setAttribute(AttributeKey.LOCALE, locale);
        return session.getAttribute(AttributeKey.CURRENT_PAGE).toString();
    }
}
