package com.example.webproject.customTag;

import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.model.entity.Admin;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminTable  extends BodyTagSupport {
    private static final int ADMINS_PER_PAGE = 10;
    private static final String CONTENT_PAGE = "property.language";
    private static final String ADMIN_LIST = "label.adminList";
    private static final String USER_LOGIN = "label.login";
    private static final String USER_PHONE_NUMBER = "label.phoneNumber";
    private static final String USER_NAME = "label.firstName";
    private static final String USER_LAST_NAME = "label.lastName";
    @Override
    public int doStartTag() {
        HttpSession session = pageContext.getSession();
        List<Admin> adminList = (List<Admin>) session.getAttribute(AttributeKey.ADMIN_DATA);
        Locale locale = new Locale((String) session.getAttribute(AttributeKey.LOCALE));
        ResourceBundle bundle = ResourceBundle.getBundle(CONTENT_PAGE, locale);
        int page = 1;
        if (session.getAttribute(AttributeKey.CURRENT_TABLE_PAGE) == null) {
            int firstPage = 1;
            session.setAttribute(AttributeKey.CURRENT_TABLE_PAGE, firstPage);
            if (adminList.size() % ADMINS_PER_PAGE == 0) {
                session.setAttribute(AttributeKey.PAGE_COUNT, adminList.size() / ADMINS_PER_PAGE);
            } else {
                session.setAttribute(AttributeKey.PAGE_COUNT, adminList.size() / ADMINS_PER_PAGE + page);
            }
        }
        int pageNumber = (int) session.getAttribute(AttributeKey.CURRENT_TABLE_PAGE);
        int firstIndex = ADMINS_PER_PAGE * (pageNumber - page);
        int lastIndex = ADMINS_PER_PAGE * pageNumber;

        JspWriter out = pageContext.getOut();
        try {
            out.write("<table class=\"table\">");
            out.write("<caption class=\"tableBox\">");
            out.write(bundle.getString(ADMIN_LIST));
            out.write("<caption>");
            out.write("<tr>");
            out.write("<th class=\"tableBox\">" + "№" + "</th>");
            out.write("<th class=\"tableBox\">" + bundle.getString(USER_LOGIN) + "</th>");
            out.write("<th class=\"tableBox\">" + bundle.getString(USER_NAME) + "</th>");
            out.write("<th class=\"tableBox\">" + bundle.getString(USER_LAST_NAME) + "</th>");
            out.write("<th class=\"tableBox\">" + bundle.getString(USER_PHONE_NUMBER) + "</th>");
            out.write("</tr>");
            for (int i = firstIndex; i < lastIndex && i < adminList.size(); i++) {
                Admin admin = adminList.get(i);
                out.write("<tr>");
                out.write("<th class=\"tableBox\">" + (i + 1));
                if (admin.getAccount().getLogin().length() > 8) {
                    out.write("<th class=\"tableBox\">" + admin.getAccount().getLogin().substring(0, 8) + "..." + "</th>");
                } else {
                    out.write("<th class=\"tableBox\">" + admin.getAccount().getLogin() + "</th>");
                }
                if (admin.getFirstName().length() > 8) {
                    out.write("<th class=\"tableBox\">" + admin.getFirstName().substring(0, 8) + "..." + "</th>");
                } else {
                    out.write("<th class=\"tableBox\">" + admin.getFirstName() + "</th>");
                }
                if (admin.getLastName().length() > 8) {
                    out.write("<th class=\"tableBox\">" + admin.getLastName().substring(0, 8) + "..." + "</th>");
                } else {
                    out.write("<th class=\"tableBox\">" + admin.getLastName() + "</th>");
                }
                out.write("<th class=\"tableBox\">" + admin.getPhoneNumber() + "</th>");
                out.write("</tr>");
            }
            out.write("</table>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
