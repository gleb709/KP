package com.example.webproject.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Payment implements Entity{
  private String login;
  private String date;
  private BigDecimal sum;
  private String operationType;

    public Payment(String login, String date, BigDecimal sum, String operationType) {
        this.login = login;
        this.date = date;
        this.sum = sum;
        this.operationType = operationType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Payment payment = (Payment) o;

        if (login == null) {
            if (payment.login != null) {
                return false;
            }
        } else {
            if (!login.equals(payment.login)) {
                return false;
            }
        }
        if (date == null) {
            if (payment.date != null) {
                return false;
            }
        } else {
            if (!date.equals(payment.date)) {
                return false;
            }
        }
        if (operationType == null) {
            if (payment.operationType != null) {
                return false;
            }
        } else {
            if (!operationType.equals(payment.operationType)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += 31 * result + (login == null ? 0 : login.hashCode());
        result += 31 * result + (date == null ? 0 : date.hashCode());
        result += 31 * result + (operationType == null ? 0 : operationType.hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(login);
        builder.append(" ");
        builder.append(date);
        builder.append(" ");
        builder.append(sum);
        builder.append(" ");
        builder.append(operationType);

        return builder.toString();
    }
}