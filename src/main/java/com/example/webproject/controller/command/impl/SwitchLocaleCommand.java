package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePass;
import com.example.webproject.controller.command.RequestParameter;
import org.w3c.dom.Attr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.smartcardio.ATR;

public class SwitchLocaleCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(RequestParameter.LOCALE);
        session.setAttribute(AttributeKey.LOCALE, locale);
        System.out.println("Current page is " + session.getAttribute(AttributeKey.CURRENT_PAGE));
        return session.getAttribute(AttributeKey.CURRENT_PAGE).toString();
    }
}
