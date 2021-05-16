package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeTablePageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeKey.CURRENT_PAGE);

        int pageCount = (int)session.getAttribute(AttributeKey.PAGE_COUNT);
        String transfer = request.getParameter(RequestParameter.TRANSFER);
        int currentTablePage = (int)session.getAttribute(AttributeKey.CURRENT_TABLE_PAGE);
        int firstPage = 1;
        int tablePage = 1;

        if(transfer.equals(RequestParameter.PREVIOUS_TABLE_PAGE) && currentTablePage > firstPage){
            session.setAttribute(AttributeKey.CURRENT_TABLE_PAGE, currentTablePage - tablePage);
        }else if(transfer.equals(RequestParameter.NEXT_TABLE_PAGE) && currentTablePage < pageCount){
            session.setAttribute(AttributeKey.CURRENT_TABLE_PAGE, currentTablePage + tablePage);
        }
        return page;
    }
}
