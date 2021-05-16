package com.example.webproject.controller;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.CommandType;
import com.example.webproject.controller.command.RequestParameter;
import org.w3c.dom.Attr;

import java.awt.print.Pageable;
import java.io.*;
import javax.print.attribute.AttributeSetUtilities;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "servlet", urlPatterns = "/controller")
public class Controller extends HttpServlet {

    public void init() { }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String page;
        boolean startKey = postTime(request);
        if(startKey) {
            session.setAttribute(AttributeKey.REQUEST_POST_TIME, request.getParameter(AttributeKey.REQUEST_POST_TIME));
            String parameter = request.getParameter(RequestParameter.ACTION_COMMAND);
            ActionCommand command = CommandType.valueOf(parameter).getCommand();
            page = command.execute(request);
            session.setAttribute(AttributeKey.CURRENT_PAGE, page);
        }else{
            page = session.getAttribute(AttributeKey.CURRENT_PAGE).toString();
        }
        request.getRequestDispatcher(page).forward(request, response);
    }

    private boolean postTime(HttpServletRequest request){
        boolean isValid = true;
        HttpSession session = request.getSession();
        if(session.getAttribute(AttributeKey.REQUEST_POST_TIME) != null){
            if(session.getAttribute(AttributeKey.REQUEST_POST_TIME).equals(request.getParameter(AttributeKey.REQUEST_POST_TIME))){
                isValid = false;
            }
        }
        return isValid;
    }

    public void destroy() {
    }
}