package com.example.webproject.model.entity;

public class User implements Entity {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Account account;
    private TariffAccount tariffAccount;

    public User(String firstName, String lastName, String phoneNumber, String email, Account account, TariffAccount tariffAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.account = account;
        this.tariffAccount = tariffAccount;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TariffAccount getTariffAccount() {
        return tariffAccount;
    }

    public void setTariffAccount(TariffAccount tariffAccount) {
        this.tariffAccount = tariffAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        User user = (User) o;
        if (firstName == null) {
            if (user.firstName != null) {
                return false;
            }
        } else {
            if (!firstName.equals(user.firstName)) {
                return false;
            }
        }
        if (lastName == null) {
            if (user.lastName != null) {
                return false;
            }
        } else {
            if (!lastName.equals(user.lastName)) {
                return false;
            }
        }
        if (phoneNumber == null) {
            if (user.phoneNumber != null) {
                return false;
            }
        } else {
            if (!phoneNumber.equals(user.phoneNumber)) {
                return false;
            }
        }
        if (email == null) {
            if (user.email != null) {
                return false;
            }
        } else {
            if (!email.equals(user.email)) {
                return false;
            }
        }
        if (account == null) {
            if (user.account != null) {
                return false;
            }
        } else {
            if (!account.equals(user.account)) {
                return false;
            }
        }

        if (tariffAccount == null) {
            if (user.tariffAccount != null) {
                return false;
            }
        } else {
            if (!tariffAccount.equals(user.tariffAccount)) {
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
        result += 31 * result + (tariffAccount == null ? 0 : tariffAccount.hashCode());

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
        builder.append(" ");
        builder.append(tariffAccount.toString());

        return builder.toString();
    }
}
