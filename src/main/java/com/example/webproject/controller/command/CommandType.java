package com.example.webproject.controller.command;

import com.example.webproject.controller.command.impl.*;

public enum CommandType {
    SIGN_IN(new SignInCommand()),
    SIGN_UP(new SignUpCommand()),
    SWITCH_LOCALE(new SwitchLocaleCommand()),
    HOME_PAGE(new HomePageCommand()),
    REGISTRATION_CONFIRMATION( new RegistrationConfirmationCommand()),
    TRANSFER(new TransferCommand());



    private ActionCommand command;
    private CommandType(ActionCommand command){
        this.command = command;
    }
    public ActionCommand getCommand(){
        return command;
    }
}
