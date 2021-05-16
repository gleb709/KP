package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferCommand implements ActionCommand {
    private static final String DEFAULT_LOCALE = "ru";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String pageParameter = request.getParameter(RequestParameter.PAGE);
        String page = PagePath.valueOf(pageParameter).getPagePath();
        session.setAttribute(AttributeKey.CURRENT_PAGE, page);
        session.setAttribute(AttributeKey.CURRENT_TABLE_PAGE, null);

        if(session.getAttribute(AttributeKey.LOCALE) == null){
            session.setAttribute(AttributeKey.LOCALE, DEFAULT_LOCALE);
        }

        if(session.getAttribute(AttributeKey.ACCOUNT_ROLE) == null){
            session.setAttribute(AttributeKey.ACCOUNT_ROLE, AttributeKey.GUEST_ROLE);
        }else{
            request.setAttribute(RequestParameter.ACCOUNT_ROLE, session.getAttribute(AttributeKey.ACCOUNT_ROLE));
        }
        return page;
    }
}
