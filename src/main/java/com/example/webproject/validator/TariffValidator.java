package com.example.webproject.validator;

import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.model.entity.Tariff;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TariffValidator {
    private static final String XSS_CHECK_PATTERN = "<script>";

    private TariffValidator(){}

    public static List<String> addTariffValidator(Tariff tariff){
        List<String> invalidData = new ArrayList<>();
        if(!xssCheck(tariff.getName())){ invalidData.add(RequestParameter.TARIFF);}
        if(!xssCheck(tariff.getInfo())){ invalidData.add(RequestParameter.TARIFF_INFO);}
        if(!xssCheck(tariff.getImage())){ invalidData.add(RequestParameter.IMAGE);}
        return invalidData;
    }

    private static boolean xssCheck(String string){
        boolean isValid = false;
        if(string != null && !string.isEmpty()){
            Pattern pattern = Pattern.compile(XSS_CHECK_PATTERN);
            Matcher matcher = pattern.matcher(string);
            if(!matcher.find()){
                isValid = true;
            }
        }
        return isValid;
    }

}
