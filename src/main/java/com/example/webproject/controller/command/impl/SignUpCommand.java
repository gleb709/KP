package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePass;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.EncryptionException;
import com.example.webproject.exception.SendMailException;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.User;
import com.example.webproject.model.service.impl.UserServiceImpl;
import com.example.webproject.util.Cryptographer;
import com.example.webproject.util.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePass.SIGN_UP_PAGE.getPagePass());
        String page = PagePass.SIGN_UP_PAGE.getPagePass();
        UserServiceImpl service = new UserServiceImpl();
        User user = new User(request.getParameter(RequestParameter.LOGIN), request.getParameter(RequestParameter.FIRST_NAME),
                request.getParameter(RequestParameter.LAST_NAME), request.getParameter(RequestParameter.CREDIT_CARD_NUMBER),
                    request.getParameter(RequestParameter.EMAIL));
        Map<String, String> password = new HashMap<>();
        password.put(RequestParameter.PASSWORD, request.getParameter(RequestParameter.PASSWORD));
        password.put(RequestParameter.REPEAT_PASSWORD, request.getParameter(RequestParameter.REPEAT_PASSWORD));
        try {
            List<String> invalidData = service.userRegistration(user, password);
            if(invalidData.isEmpty()){
                session.setAttribute(RequestParameter.USER_DATA, user);
                Cryptographer cryptographer = new Cryptographer();
                String sendTo = request.getParameter(RequestParameter.EMAIL);
                String mailSubject = "Confirmation";
                String mailText = cryptographer.encrypt(request.getParameter(RequestParameter.LOGIN));
                String encryptedPassword = cryptographer.encrypt(request.getParameter(RequestParameter.PASSWORD));
                MailSender mailSender = new MailSender(sendTo, mailSubject, mailText);
                mailSender.send();
                session.setAttribute(RequestParameter.CONFIRMATION_KEY, mailText);
                session.setAttribute(RequestParameter.PASSWORD, encryptedPassword);
                page = PagePass.EMAIL_CONFIRMATION.getPagePass();
                System.out.println("SignUp command");
            }else{
                session.setAttribute(RequestParameter.INCORRECT_DATA, invalidData);
            }
        } catch (ServiceException | SendMailException | EncryptionException e) {
            e.printStackTrace();
        }
        return page;
    }
}
