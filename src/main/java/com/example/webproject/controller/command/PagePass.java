package com.example.webproject.controller.command;

public enum PagePass {
    HOME_PAGE("/page/home.jsp"),
    SIGN_UP_PAGE("/page/signUp.jsp"),
    SIGN_IN_PAGE("/page/signIn.jsp"),
    USER_MENU("/page/userMenu.jsp"),
    EMAIL_CONFIRMATION("/page/mailConfirmation.jsp");

    private String pagePass;

    private PagePass(String pagePass){
        this.pagePass = pagePass;
    }

    public String getPagePass(){
        return pagePass;
    }
}
