package com.example.webproject.model.entity;

import java.util.Objects;

public class Admin implements Entity{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Account account;

    public Admin(String firstName, String lastName, String phoneNumber, String email, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Admin admin = (Admin) o;
        if (firstName == null) {
            if (admin.firstName != null) {
                return false;
            }
        } else {
            if (!firstName.equals(admin.firstName)) {
                return false;
            }
        }
        if (lastName == null) {
            if (admin.lastName != null) {
                return false;
            }
        } else {
            if (!lastName.equals(admin.lastName)) {
                return false;
            }
        }
        if (phoneNumber == null) {
            if (admin.phoneNumber != null) {
                return false;
            }
        } else {
            if (!phoneNumber.equals(admin.phoneNumber)) {
                return false;
            }
        }
        if (email == null) {
            if (admin.email != null) {
                return false;
            }
        } else {
            if (!email.equals(admin.email)) {
                return false;
            }
        }
        if (account == null) {
            if (admin.account != null) {
                return false;
            }
        } else {
            if (!account.equals(admin.account)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += 31 * result + (firstName == null ? 0 : firstName.hashCode());
        result += 31 * result + (lastName == null ? 0 : lastName.hashCode());
        result += 31 * result + (phoneNumber == null ? 0 : phoneNumber.hashCode());
        result += 31 * result + (email == null ? 0 : email.hashCode());
        result += 31 * result + (account == null ? 0 : account.hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(firstName);
        builder.append(" ");
        builder.append(lastName);
        builder.append(" ");
        builder.append(phoneNumber);
        builder.append(" ");
        builder.append(email);
        builder.append(" ");
        builder.append(account.toString());

        return builder.toString();
    }
}
