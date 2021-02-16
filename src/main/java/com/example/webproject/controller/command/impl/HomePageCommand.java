package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.CommandType;
import com.example.webproject.controller.command.PagePass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.smartcardio.ATR;
import java.util.HashMap;
import java.util.Map;

public class HomePageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePass.HOME_PAGE);
        return PagePass.HOME_PAGE.getPagePass();
    }
}
