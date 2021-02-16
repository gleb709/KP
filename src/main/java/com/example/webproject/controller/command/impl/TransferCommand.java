package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePass;
import com.example.webproject.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TransferCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String pageParameter = request.getParameter(RequestParameter.PAGE);
        String page = PagePass.valueOf(pageParameter).getPagePass();
        session.setAttribute(AttributeKey.CURRENT_PAGE, page);
        return page;
    }
}
