package com.example.webproject.validator;

import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String PASSWORD_PATTERN = "([A-Za-z0-9]{8,30})";
    private static final String LOGIN_PATTERN = "([A-Za-z0-9._-]{8,30})";
    private static final String NAME_PATTERN = "^([А-Я]{1}[а-яё]{1,29}|[A-Z]{1}[a-z]{1,29})$";
    private static final String CREDIT_CARD = "[0-9]{8}";
    private static final String XSS_CHECK = "<script>";
    private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@+\\p{Alnum}+\\.\\p{Alpha}{2,4}$";

    private UserValidator(){}


    public static List<String> signUpValidator(User user, Map<String, String> password){
        List<String> invalidData = new ArrayList<>();
        if(!loginValidator(user.getUserLogin())){ invalidData.add(RequestParameter.LOGIN);}
        if(!passwordValidator(password.get(RequestParameter.PASSWORD))){ invalidData.add(RequestParameter.PASSWORD);}
        if(!comparePassword(password)){ invalidData.add(RequestParameter.REPEAT_PASSWORD);}
        if(!firstNameValidator(user.getUserFirstName())){ invalidData.add(RequestParameter.FIRST_NAME);}
        if(!lastNameValidator(user.getUserLastName())){ invalidData.add(RequestParameter.LAST_NAME);}
        if(!creditCardValidator(user.getUserCreditCard())) { invalidData.add(RequestParameter.CREDIT_CARD_NUMBER);}
        if(!emailValidator(user.getUserMailAddress())){ invalidData.add(RequestParameter.EMAIL);}
       return invalidData;
    }

    public static List<String> signInValidator(String login, String password){
        List<String> invalidData = new ArrayList<>();
        if(!loginValidator(login)){ invalidData.add(RequestParameter.LOGIN);}
        if(!passwordValidator(password)){ invalidData.add(RequestParameter.PASSWORD);}
        return invalidData;
    }

    public static boolean creditCardValidator(String creditCard){
        boolean isValid = false;
        if(creditCard != null && !creditCard.isEmpty()){
            Pattern pattern = Pattern.compile(XSS_CHECK);
            Matcher matcher = pattern.matcher(creditCard);
            if(!matcher.find()){
                pattern = Pattern.compile(CREDIT_CARD);
                matcher = pattern.matcher(creditCard);
                isValid = matcher.matches();
            }
        }
        return isValid;
    }

    public static boolean emailValidator(String email){
        boolean isValid = false;
        if(email != null && !email.isEmpty()) {
            Pattern pattern = Pattern.compile(XSS_CHECK);
            Matcher matcher = pattern.matcher(email);
            if(!matcher.find()){
                pattern = Pattern.compile(EMAIL_PATTERN);
                matcher = pattern.matcher(email);
                isValid = matcher.matches();
            }
        }
        return isValid;
    }

    public static boolean passwordValidator(String password){
        boolean isValid = false;
        if(password != null && !password.isEmpty()) {
            Pattern pattern = Pattern.compile(XSS_CHECK);
            Matcher matcher = pattern.matcher(password);
            if(!matcher.find()){
                pattern = Pattern.compile(PASSWORD_PATTERN);
                matcher = pattern.matcher(password);
                isValid = matcher.matches();
            }
        }
        return isValid;
    }

    public static boolean comparePassword(Map<String,String> password){
        boolean isValid = false;
        if(password.get(RequestParameter.PASSWORD).equals(password.get(RequestParameter.REPEAT_PASSWORD))){
            isValid = true;
        }
        return isValid;
    }

    public static boolean loginValidator(String login){
        boolean isValid = true;
        Pattern pattern = Pattern.compile(XSS_CHECK);
        Matcher matcher = pattern.matcher(login);
        if(!matcher.find()){
            pattern = Pattern.compile(LOGIN_PATTERN);
            matcher = pattern.matcher(login);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static boolean firstNameValidator(String firstName){
        boolean isValid = false;
        if(firstName != null && !firstName.isEmpty()) {
            Pattern pattern = Pattern.compile(XSS_CHECK);
            Matcher matcher = pattern.matcher(firstName);
            if(!matcher.find()){
                pattern = Pattern.compile(NAME_PATTERN);
                matcher = pattern.matcher(firstName);
                isValid = matcher.matches();
            }
        }
        return isValid;
    }

    public static boolean lastNameValidator(String lastName){
        boolean isValid = false;
        if(lastName != null && !lastName.isEmpty()){
            Pattern pattern = Pattern.compile(XSS_CHECK);
            Matcher matcher = pattern.matcher(lastName);
            if(!matcher.find()){
                pattern = Pattern.compile(NAME_PATTERN);
                matcher = pattern.matcher(lastName);
                isValid = matcher.matches();
            }
        }
        return isValid;
    }
}
