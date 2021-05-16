package com.example.webproject.customTag;

import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.model.entity.Tariff;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomePageTable extends BodyTagSupport {
    private static final int TARIFF_PER_PAGE = 3;
    private static final String CONTENT_PAGE = "property.language";
    private static final String TARIFF_PRICE = "label.tariffPrice";
    private static final String CHANGE_TARIFF = "button.changeTariff";


    @Override
    public int doStartTag() {
        HttpSession session = pageContext.getSession();
        List<Tariff> tariffList = (List<Tariff>) session.getAttribute(AttributeKey.TARIFF_DATA);
        Locale locale = new Locale((String) session.getAttribute(AttributeKey.LOCALE));
        ResourceBundle bundle = ResourceBundle.getBundle(CONTENT_PAGE, locale);
        int page = 1;
        if(session.getAttribute(AttributeKey.CURRENT_TABLE_PAGE) == null){
            int firstPage = 1;
            session.setAttribute(AttributeKey.CURRENT_TABLE_PAGE, firstPage);
            if(tariffList.size()%TARIFF_PER_PAGE == 0) {
                session.setAttribute(AttributeKey.PAGE_COUNT, tariffList.size() / TARIFF_PER_PAGE);
            }else{
                session.setAttribute(AttributeKey.PAGE_COUNT, tariffList.size() / TARIFF_PER_PAGE + page);
            }
        }
        int pageNumber = (int)session.getAttribute(AttributeKey.CURRENT_TABLE_PAGE);
        int firstIndex = TARIFF_PER_PAGE * (pageNumber-page);
        int lastIndex = TARIFF_PER_PAGE * pageNumber;

        JspWriter out = pageContext.getOut();
        try {
            for(int i = firstIndex; i < lastIndex && i < tariffList.size(); i++) {
                Tariff tariff = tariffList.get(i);
                out.write("<div class=\"tariffBox\">\n" +
                        "                    <img class=\"tariffImage\" src=\"\"/>\n" +
                        "                    <label class=\"priceLabel\">" + tariff.getName() + "</label>\n" +
                        "                    <label class=\"priceLabel\">" + bundle.getString(TARIFF_PRICE) + ":" + tariff.getPrice() + "</label>\n" +
                        "                    <form class=\"actionForm1\" action=\"controller\" method=\"post\">\n" +
                        "                        <input class=\"buyButton\" type=\"submit\" value=\""+ bundle.getString(CHANGE_TARIFF)+ "\"/>\n" +
                        "                        <input type=\"hidden\" name=\"command\" value=\"TARIFF_HOME_INFO\"/>\n" +
                        "                        <input type=\"hidden\" name=\"tariff\" value=\""+ tariff.getName() +"\"/>\n" +
                        "                    </form>\n" +
                        "                </div>\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
