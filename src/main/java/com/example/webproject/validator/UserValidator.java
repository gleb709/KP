package com.example.webproject.validator;

import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.model.entity.Admin;
import com.example.webproject.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String PASSWORD_PATTERN = "([A-Za-z0-9]{8,30})";
    private static final String EMAIL_KEY_PATTERN = "([A-Z0-9]{10,150})";
    private static final String LOGIN_PATTERN = "([A-Za-z0-9._-]{4,30})";
    private static final String NAME_PATTERN = "^([А-Я]{1}[а-яё]{1,29}|[A-Z]{1}[a-z]{1,29})$";
    private static final String CREDIT_CARD_PATTERN = "[0-9]{16}";
    private static final String CREDIT_CARD_CODE_PATTERN = "[0-9]{3}";
    private static final String CREDIT_CARD_NAME_PATTERN = "([A-Z]{2,20}[ ]{1}[A-Z]{2,20})";
    private static final String XSS_CHECK_PATTERN = "<script>";
    private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@+\\p{Alnum}+\\.\\p{Alpha}{2,4}$";
    private static final String CONTRACT_NUMBER_PATTERN = "([0-9]{8})";
    private static final String PHONE_PATTERN = "([+]{1}[0-9]{12})";
    private static final String SUM_PATTERN = "([0-9]{0,20}[.]{0,1}[0-9]{0,2})";
    private static final String DAY_COUNT_PATTERN = "([0-9]{1,10})";

    private UserValidator(){}

    public static List<String> signUpValidator(User user, Map<String, String> password){
        List<String> invalidData = new ArrayList<>();
        if(!loginValidator(user.getAccount().getLogin())){ invalidData.add(RequestParameter.LOGIN);}
        if(!passwordValidator(password.get(RequestParameter.PASSWORD))){ invalidData.add(RequestParameter.PASSWORD);}
        if(!comparePassword(password.get(RequestParameter.PASSWORD), password.get(RequestParameter.REPEAT_PASSWORD))){ invalidData.add(RequestParameter.REPEAT_PASSWORD);}
        if(!firstNameValidator(user.getFirstName())){ invalidData.add(RequestParameter.FIRST_NAME);}
        if(!lastNameValidator(user.getLastName())){ invalidData.add(RequestParameter.LAST_NAME);}
        if(!emailValidator(user.getEmail())){ invalidData.add(RequestParameter.EMAIL);}
        if(!phoneValidator(user.getPhoneNumber())){ invalidData.add(RequestParameter.PHONE_NUMBER);}
        if(!contractNumberValidator(user.getTariffAccount().getContractNumber())){ invalidData.add(RequestParameter.CONTRACT_NUMBER);}
        return invalidData;
    }

    public static List<String> signUpAdminValidator(Admin admin, Map<String, String> password){
        List<String> invalidData = new ArrayList<>();
        if(!loginValidator(admin.getAccount().getLogin())){ invalidData.add(RequestParameter.LOGIN);}
        if(!passwordValidator(password.get(RequestParameter.PASSWORD))){ invalidData.add(RequestParameter.PASSWORD);}
        if(!comparePassword(password.get(RequestParameter.PASSWORD), password.get(RequestParameter.REPEAT_PASSWORD))){ invalidData.add(RequestParameter.REPEAT_PASSWORD);}
        if(!firstNameValidator(admin.getFirstName())){ invalidData.add(RequestParameter.FIRST_NAME);}
        if(!lastNameValidator(admin.getLastName())){ invalidData.add(RequestParameter.LAST_NAME);}
        if(!emailValidator(admin.getEmail())){ invalidData.add(RequestParameter.EMAIL);}
        if(!phoneValidator(admin.getPhoneNumber())){ invalidData.add(RequestParameter.PHONE_NUMBER);}
        return invalidData;
    }

    public static List<String> accountActivationValidator(String login, String key){
        List<String> invalidData = new ArrayList<>();
        if(!loginValidator(login)){ invalidData.add("login");}
        if(!emailKeyCheck(key)){ invalidData.add("key"); }
        return invalidData;
    }

    public static List<String> transactionValidator(Map<String, String> transactionForm){
        List<String> invalidData = new ArrayList<>();
        if(!creditCardNumberValidator(transactionForm.get(RequestParameter.CREDIT_CARD_NUMBER))){ invalidData.add(RequestParameter.CREDIT_CARD_NUMBER);}
        if(!creditCardNameValidator(transactionForm.get(RequestParameter.CREDIT_CARD_NAME))){ invalidData.add(RequestParameter.CREDIT_CARD_NAME);}
        if(!creditCardCodeValidator(transactionForm.get(RequestParameter.CREDIT_CARD_CODE))){ invalidData.add(RequestParameter.CREDIT_CARD_CODE);}
        if(!sumValidator(transactionForm.get(RequestParameter.SUM))){ invalidData.add(RequestParameter.SUM);}
        return invalidData;
    }
/*
    public static List<String> paymentFormValidator(Payment paymentForm){
        List<String> invalidData = new ArrayList<>();
        if(!dayCountValidator(paymentForm.getDayCount())){ invalidData.add(RequestParameter.DAY_COUNT);}
        return invalidData;
    }


 */
    public static List<String> changePasswordValidator(Map<String, String> password){
        List<String> invalidData = new ArrayList<>();
        if(!passwordValidator(password.get(RequestParameter.NEW_PASSWORD))){ invalidData.add(RequestParameter.NEW_PASSWORD);}
        if(!passwordValidator(password.get(RequestParameter.PASSWORD))){ invalidData.add(RequestParameter.PASSWORD);}
        if(!comparePassword(password.get(RequestParameter.NEW_PASSWORD), password.get(RequestParameter.REPEAT_PASSWORD))){ invalidData.add(RequestParameter.REPEAT_PASSWORD);}
        if(comparePassword(password.get(RequestParameter.PASSWORD), password.get(RequestParameter.NEW_PASSWORD))){ invalidData.add(RequestParameter.THE_SAME);}
        return invalidData;
    }

    public static List<String> changeForgotPasswordValidator(Map<String, String> password){
        List<String> invalidData = new ArrayList<>();
        if(!passwordValidator(password.get(RequestParameter.PASSWORD))){ invalidData.add(RequestParameter.PASSWORD);}
        if(!comparePassword(password.get(RequestParameter.PASSWORD), password.get(RequestParameter.NEW_PASSWORD))){ invalidData.add(RequestParameter.REPEAT_PASSWORD);}
        return invalidData;
    }

    public static List<String> signInValidator(String login, String password){
        List<String> invalidData = new ArrayList<>();
        if(!loginValidator(login)){ invalidData.add(RequestParameter.LOGIN);}
        if(!passwordValidator(password)){ invalidData.add(RequestParameter.PASSWORD);}
        return invalidData;
    }

    public static boolean phoneValidator(String phoneNumber){
        boolean isValid = false;
        if(xssCheck(phoneNumber)){
            Pattern pattern = Pattern.compile(PHONE_PATTERN);
            Matcher matcher = pattern.matcher(phoneNumber);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static boolean contractNumberValidator(String contractNumber){
        boolean isValid = false;
        if(xssCheck(contractNumber)){
            Pattern pattern = Pattern.compile(CONTRACT_NUMBER_PATTERN);
            Matcher matcher = pattern.matcher(contractNumber);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static boolean dayCountValidator(String dayCount){
        boolean isValid = false;
        if(xssCheck(dayCount)){
            Pattern pattern = Pattern.compile(DAY_COUNT_PATTERN);
            Matcher matcher = pattern.matcher(dayCount);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static boolean sumValidator(String sum){
        boolean isvalid = false;
        if(xssCheck(sum)){
            Pattern pattern = Pattern.compile(SUM_PATTERN);
            Matcher matcher = pattern.matcher(sum);   //todo
            isvalid = matcher.matches();
        }
        return isvalid;
    }

    public static boolean emailKeyCheck(String key){
        boolean isValid = false;
        if(xssCheck(key)){
            Pattern pattern = Pattern.compile(EMAIL_KEY_PATTERN);
            Matcher matcher = pattern.matcher(key);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static boolean creditCardNumberValidator(String creditCard){
        boolean isValid = false;
        if(xssCheck(creditCard)){
            Pattern pattern = Pattern.compile(CREDIT_CARD_PATTERN);
            Matcher matcher = pattern.matcher(creditCard);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static boolean creditCardNameValidator(String creditCardName){
        boolean isValid = false;
        if(xssCheck(creditCardName)){
            Pattern pattern = Pattern.compile(CREDIT_CARD_NAME_PATTERN);
            Matcher matcher = pattern.matcher(creditCardName);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static boolean creditCardCodeValidator(String creditCardCode) {
        boolean isValid = false;
        if(xssCheck(creditCardCode)){
            Pattern pattern = Pattern.compile(CREDIT_CARD_CODE_PATTERN);
            Matcher matcher = pattern.matcher(creditCardCode);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static boolean emailValidator(String email){
        boolean isValid = false;

        if(email != null && !email.isEmpty()) {
            if(xssCheck(email)){
                Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(email);
                isValid = matcher.matches();
            }
        }
        return isValid;
    }

    public static boolean passwordValidator(String password){
        boolean isValid = false;
        if(xssCheck(password)){
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(password);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static boolean comparePassword(String password, String repeatPassword){
        boolean isValid = false;
        if(xssCheck(password)){
            if(xssCheck(repeatPassword)){
                if(password.equals(repeatPassword)){
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    public static boolean loginValidator(String login){
        boolean isValid = false;
        if(xssCheck(login)){
            Pattern pattern = Pattern.compile(LOGIN_PATTERN);
            Matcher matcher = pattern.matcher(login);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static boolean firstNameValidator(String firstName){
        boolean isValid = false;
        if(xssCheck(firstName)){
            Pattern pattern = Pattern.compile(NAME_PATTERN);
            Matcher matcher = pattern.matcher(firstName);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static boolean lastNameValidator(String lastName){
        boolean isValid = false;
        if(xssCheck(lastName)){
            Pattern pattern = Pattern.compile(NAME_PATTERN);
            Matcher matcher = pattern.matcher(lastName);
            isValid = matcher.matches();
        }
        return isValid;
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
