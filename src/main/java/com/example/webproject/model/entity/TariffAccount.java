package com.example.webproject.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TariffAccount implements Entity{
    private String userLogin;
    private BigDecimal balance;
    private String tariff;
    private int paidUpDays;
    private String contractNumber;

    public TariffAccount(String userLogin, BigDecimal balance, String tariff, int paidUpDays, String contractNumber) {
        this.userLogin = userLogin;
        this.balance = balance;
        this.tariff = tariff;
        this.paidUpDays = paidUpDays;
        this.contractNumber = contractNumber;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getPaidUpDays() {
        return paidUpDays;
    }

    public void setPaidUpDays(int paidUpDays) {
        this.paidUpDays = paidUpDays;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        TariffAccount tariffAccount = (TariffAccount) o;

        if (userLogin == null) {
            if (tariffAccount.userLogin != null) {
                return false;
            }
        } else {
            if (!userLogin.equals(tariffAccount.userLogin)) {
                return false;
            }
        }
        if (contractNumber == null) {
            if (tariffAccount.contractNumber != null) {
                return false;
            }
        } else {
            if (!contractNumber.equals(tariffAccount.contractNumber)) {
                return false;
            }
        }
        if (tariff == null) {
            if (tariffAccount.tariff != null) {
                return false;
            }
        } else {
            if (!tariff.equals(tariffAccount.tariff)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += 31 * result + (userLogin == null ? 0 : userLogin.hashCode());
        result += 31 * result + (contractNumber == null ? 0 : contractNumber.hashCode());
        result += 31 * result + (tariff == null ? 0 : tariff.hashCode());
        result += 31 * Integer.hashCode(paidUpDays);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(userLogin);
        builder.append(" ");
        builder.append(balance);
        builder.append(" ");
        builder.append(tariff);
        builder.append(" ");
        builder.append(paidUpDays);
        builder.append(" ");
        builder.append(contractNumber);

        return builder.toString();
    }
}
