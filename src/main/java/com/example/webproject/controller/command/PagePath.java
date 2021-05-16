package com.example.webproject.controller.command;

public enum PagePath {
    HOME_PAGE("/page/home.jsp"),
    SIGN_UP_PAGE("/page/signUp.jsp"),
    SIGN_IN_PAGE("/page/signIn.jsp"),
    USER_MENU("/page/userMenu.jsp"),
    CHANGE_PASSWORD("/page/changePassword.jsp"),
    CHANGE_FORGOT_PASSWORD("/page/changeForgotPassword.jsp"),
    FORGOT_PASSWORD("/page/forgotPassword.jsp"),
    CHANGE_PASSWORD_CONFIRMATION("/page/changePasswordConfirmation.jsp"),
    TRANSACTION("/page/transaction.jsp"),
    USER_TARIFF_INFO("/page/userTariffInfo.jsp"),
    HOME_TARIFF_INFO("/page/tariffHomeInfo.jsp"),
    ADMIN_LIST("/page/adminList.jsp"),
    ADMIN_ACCOUNT_INFO("/page/adminAccountInfo.jsp"),
    ADMIN_INFO("/page/adminInfo.jsp"),
    TARIFF_PAYMENT_INFO("/page/tariffPaymentInfo.jsp"),
    TARIFF_INFO("/page/tariffInfo.jsp"),
    TARIFF_HISTORY("/page/tariffHistory.jsp"),
    SERVICES_PAYMENT("/page/servicesPayment.jsp"),
    TARIFF_PAYMENT("/page/tariffPayment.jsp"),
    SUBSCRIPTION_FEE("/page/tariffSubscriptionFee.jsp"),
    USER_INFO("/page/userInfo.jsp"),
    EMAIL_CONFIRMATION("/page/mailConfirmation.jsp"),
    ACCOUNT_ACTIVATION("/page/accountActivation.jsp"),
    USER_LIST("/page/userList.jsp"),
    ADD_ADMIN("/page/addAdmin.jsp"),
    BLOCKED_USER_LIST("page/blockedUsers.jsp"),
    DEBTORS_LIST("/page/debtors.jsp"),
    USER_ACCOUNT_INFO("/page/userAccountInfo.jsp"),
    TARIFF_LIST("/page/tariffList.jsp"),
    CHANGE_TARIFF_INFO("page/changeTariffInfo.jsp"),
    ADD_TARIFF("/page/addTariff.jsp"),
    DELETE_TARIFF("/page/deleteTariff.jsp"),
    SETTINGS("/page/settings.jsp");
    private String pagePath;

    private PagePath(String pagePath){
        this.pagePath = pagePath;
    }

    public String getPagePath(){
        return pagePath;
    }
}
