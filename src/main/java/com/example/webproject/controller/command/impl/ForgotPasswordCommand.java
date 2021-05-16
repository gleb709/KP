package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.EncryptionException;
import com.example.webproject.exception.SendMailException;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.service.impl.UserServiceImpl;
import com.example.webproject.util.Cryptographer;
import com.example.webproject.util.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ForgotPasswordCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.FORGOT_PASSWORD.getPagePath());
        UserServiceImpl service = new UserServiceImpl();
        String page = PagePath.FORGOT_PASSWORD.getPagePath();

        String userEmail = request.getParameter(RequestParameter.EMAIL);

        try{
            List<String> invalidData = service.checkUserEmail(userEmail);
            if (invalidData.isEmpty()) {
                Cryptographer cryptographer = new Cryptographer();
                String sendTo = userEmail;
                String mailSubject = "Password recovery";//todo
                String mailText = cryptographer.encrypt(userEmail);
                MailSender mailSender = new MailSender(sendTo, mailSubject, mailText);
                mailSender.send();
                session.setAttribute(AttributeKey.CONFIRMATION_KEY, mailText);
                session.setAttribute(AttributeKey.EMAIL, sendTo);
                page = PagePath.CHANGE_PASSWORD_CONFIRMATION.getPagePath();
            } else {
                request.setAttribute(RequestParameter.INCORRECT_DATA, invalidData);
            }
        } catch (ServiceException | EncryptionException | SendMailException e) {
            e.printStackTrace();
        }
        return page;
    }
}
