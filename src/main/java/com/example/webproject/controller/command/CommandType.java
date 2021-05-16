package com.example.webproject.controller.command;

import com.example.webproject.controller.command.impl.*;

public enum CommandType {
    SIGN_IN(new SignInCommand()),
    SIGN_UP(new SignUpCommand()),
    SWITCH_LOCALE(new SwitchLocaleCommand()),
    ACCOUNT_ACTIVATION(new AccountActivationCommand()),
    ADMIN_INFO(new AdminInfoCommand()),
    ADMIN_LIST(new AdminListCommand()),
    ADMIN_ACCOUNT_INFO(new AdminAccountInfoCommand()),
    TARIFF_LIST(new TariffListCommand()),
    ADD_TARIFF(new AddTariffCommand()),
    DELETE_TARIFF(new DeleteTariffCommand()),
    DELETE_ACCOUNT(new DeleteAccountCommand()),
    TARIFF_PAYMENT(new TariffPaymentCommand()),
    TARIFF_HOME_INFO(new TariffHomeInfoCommand()),
    CHANGE_TARIFF(new ChangeTariffCommand()),
    PAYMENT(new PaymentCommand()),
    TRANSACTION(new TransactionCommand()),
    USER_TARIFF_INFO(new UserTariffInfoCommand()),
    TARIFF_INFO(new TariffInfoCommand()),
    CHANGE_TARIFF_INFO(new ChangeTariffInfoCommand()),
    HOME_PAGE(new HomePageCommand()),
    GET_PAYMENT(new GetPaymentCommand()),
    SUBSCRIPTION_FEE(new SubscriptionFeeCommand()),
    COLLECT_SUBSCRIPTION_FEE(new CollectionSubscriptionFeeCommand()),
    USER_INFO(new UserInfoCommand()),
    CHANGE_FORGOT_PASSWORD(new ChangeForgotPasswordCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    FORGOT_PASSWORD(new ForgotPasswordCommand()),
    USER_LIST(new UserListCommand()),
    BLOCKED_USERS(new BlockedUsersTableCommand()),
    CHANGE_TABLE_PAGE(new ChangeTablePageCommand()),
    USER_ACCOUNT_INFO(new UserAccountInfoCommand()),
    UNBLOCK_ACCOUNT(new UnblockAccountCommand()),
    BLOCK_ACCOUNT(new BlockAccountCommand()),
    LOG_OUT(new LogOutCommand()),
    TRANSFER(new TransferCommand());

    private ActionCommand command;
    private CommandType(ActionCommand command){
        this.command = command;
    }
    public ActionCommand getCommand(){
        return command;
    }

}
