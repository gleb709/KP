package com.example.webproject.model.entity;

import java.util.Objects;

public class Account implements Entity{
    private String login;
    private String status;
    private String role;

    public Account(String login, String status, String role) {
        this.login = login;
        this.status = status;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;

        if (login == null) {
            if (account.login != null) {
                return false;
            }
        } else {
            if (!login.equals(account.login)) {
                return false;
            }
        }
        if (status == null) {
            if (account.status != null) {
                return false;
            }
        } else {
            if (!status.equals(account.status)) {
                return false;
            }
        }
        if (role == null) {
            if (account.role != null) {
                return false;
            }
        } else {
            if (!role.equals(account.role)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += result * 31 + (login == null ? 0 : login.hashCode());
        result += result * 31 + (status == null ? 0 : status.hashCode());
        result += result * 31 + (role == null ? 0 : role.hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(login);
        builder.append(" ");
        builder.append(role);
        builder.append(" ");
        builder.append(status);

        return builder.toString();
    }
}
