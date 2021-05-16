package com.example.webproject.controller.command.impl;

import com.example.webproject.controller.command.ActionCommand;
import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.controller.command.PagePath;
import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.EncryptionException;
import com.example.webproject.exception.SendMailException;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Account;
import com.example.webproject.model.entity.Admin;
import com.example.webproject.model.entity.TariffAccount;
import com.example.webproject.model.entity.User;
import com.example.webproject.model.service.impl.UserServiceImpl;
import com.example.webproject.util.Cryptographer;
import com.example.webproject.util.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpCommand implements ActionCommand {
    private static final String DEFAULT_STATUS = "isWaiting";
    private static final String DEFAULT_TARIFF = "no tariff";
    private static final int PAID_DAYS = 0;
    private static final int DEFAULT_BALANCE = 0;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserServiceImpl service = new UserServiceImpl();
        session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.SIGN_UP_PAGE.getPagePath());
        String page = PagePath.SIGN_UP_PAGE.getPagePath();

        if(request.getParameter(RequestParameter.ACCOUNT_ROLE).equals(RequestParameter.USER_ROLE)) {
            TariffAccount tariffAccount = new TariffAccount(request.getParameter(RequestParameter.LOGIN), BigDecimal.valueOf(DEFAULT_BALANCE),
                    DEFAULT_TARIFF, PAID_DAYS, request.getParameter(RequestParameter.CONTRACT_NUMBER));
            Account account = new Account(request.getParameter(RequestParameter.LOGIN), DEFAULT_STATUS,
                    request.getParameter(RequestParameter.ACCOUNT_ROLE));
            User user = new User(request.getParameter(RequestParameter.FIRST_NAME),
                    request.getParameter(RequestParameter.LAST_NAME), request.getParameter(RequestParameter.PHONE_NUMBER),
                    request.getParameter(RequestParameter.EMAIL), account, tariffAccount);

            Map<String, String> password = new HashMap<>();
            password.put(RequestParameter.PASSWORD, request.getParameter(RequestParameter.PASSWORD));
            password.put(RequestParameter.REPEAT_PASSWORD, request.getParameter(RequestParameter.REPEAT_PASSWORD));

            try {
                List<String> invalidData = service.userRegistration(user, password);
                if(invalidData.isEmpty()){
                    Cryptographer cryptographer = new Cryptographer();
                    String sendTo = request.getParameter(RequestParameter.EMAIL);
                    String mailSubject = "Confirmation";
                    String mailText = cryptographer.encrypt(request.getParameter(RequestParameter.LOGIN));
                    MailSender mailSender = new MailSender(sendTo, mailSubject, mailText);
                    mailSender.send();
                    session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.HOME_PAGE.getPagePath());
                    page = PagePath.HOME_PAGE.getPagePath();
                }else{
                    request.setAttribute(RequestParameter.INCORRECT_DATA, invalidData);
                }
            } catch (ServiceException | SendMailException | EncryptionException e) {
                e.printStackTrace();
            }
        }

        if(request.getParameter(RequestParameter.ACCOUNT_ROLE).equals(RequestParameter.ADMIN_ROLE)) {
            Account account = new Account(request.getParameter(RequestParameter.LOGIN), DEFAULT_STATUS,
                    request.getParameter(RequestParameter.ACCOUNT_ROLE));
            Admin admin = new Admin(request.getParameter(RequestParameter.FIRST_NAME), request.getParameter(RequestParameter.LAST_NAME),
                    request.getParameter(RequestParameter.PHONE_NUMBER), request.getParameter(RequestParameter.EMAIL), account);

            Map<String, String> password = new HashMap<>();
            password.put(RequestParameter.PASSWORD, request.getParameter(RequestParameter.PASSWORD));
            password.put(RequestParameter.REPEAT_PASSWORD, request.getParameter(RequestParameter.REPEAT_PASSWORD));

            try {
                List<String> invalidData = service.adminRegistration(admin, password);
                if(invalidData.isEmpty()){
                    Cryptographer cryptographer = new Cryptographer();
                    String sendTo = request.getParameter(RequestParameter.EMAIL);
                    String mailSubject = "Confirmation"; //todo
                    String mailText = cryptographer.encrypt(request.getParameter(RequestParameter.LOGIN));
                    MailSender mailSender = new MailSender(sendTo, mailSubject, mailText);
                    mailSender.send();
                    session.setAttribute(AttributeKey.CURRENT_PAGE, PagePath.HOME_PAGE.getPagePath());
                    page = PagePath.HOME_PAGE.getPagePath();
                }else{
                    request.setAttribute(RequestParameter.INCORRECT_DATA, invalidData);
                }
            } catch (ServiceException | SendMailException | EncryptionException e) {
                e.printStackTrace();
            }
        }
        return page;
    }
}
