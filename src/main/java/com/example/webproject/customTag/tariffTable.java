package com.example.webproject.customTag;

import com.example.webproject.controller.command.AttributeKey;
import com.example.webproject.model.entity.Tariff;
import com.example.webproject.model.entity.User;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class tariffTable extends BodyTagSupport {
    private static final int TARIFF_PER_PAGE = 10;
    private static final String CONTENT_PAGE = "property.language";
    private static final String TARIFF_LIST = "label.tariffList";
    private static final String TARIFF = "label.tariff";
    private static final String TARIFF_PRICE = "label.tariffPrice";
    private static final String TARIFF_DISCOUNT = "label.tariffDiscount";
    private static final String TARIFF_INFO = "label.tariffInfo";

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
            out.write("<table class=\"table\">");
            out.write("<caption class=\"tableBox\">");
            out.write(bundle.getString(TARIFF_LIST));
            out.write("<caption>");
            out.write("<tr>");
            out.write("<th class=\"tableBox\">" + "№" + "</th>");
            out.write("<th class=\"tableBox\">" + bundle.getString(TARIFF) + "</th>");
            out.write("<th class=\"tableBox\">" + bundle.getString(TARIFF_PRICE) + "</th>");
            out.write("<th class=\"tableBox\">" + bundle.getString(TARIFF_DISCOUNT) + "</th>");
            out.write("<th class=\"tableBox\">" + bundle.getString(TARIFF_INFO) + "</th>");
            out.write("</tr>");
            for(int i = firstIndex; i < lastIndex && i < tariffList.size(); i++) {
                Tariff tariff = tariffList.get(i);
                out.write("<tr>");
                out.write("<th class=\"tableBox\">" + (i+1));
                if(tariff.getName().length() > 15){
                    out.write("<th class=\"tableBox\">" + tariff.getName().substring(0,15) + "..." + "</th>");
                }else {
                    out.write("<th class=\"tableBox\">" + tariff.getName() + "</th>");
                }
                out.write("<th class=\"tableBox\">" + tariff.getPrice() + "</th>");
                out.write("<th class=\"tableBox\">" + tariff.getDiscount() + "</th>");
                if(tariff.getInfo().length() > 40){
                    out.write("<th class=\"tableBox\">" + tariff.getInfo().substring(0,40)+ "..." + "</th>");
                }else {
                    out.write("<th class=\"tableBox\">" + tariff.getInfo() + "</th>");
                }
                out.write("</tr>");
            }
            out.write("</table>");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}

