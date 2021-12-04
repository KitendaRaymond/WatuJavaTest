package com.example.wetu;

import java.io.Serializable;
import java.math.BigDecimal;

public class Balance implements Serializable
{
    private Integer loan_id;
    private BigDecimal principal_amount, totalamount, balance;

    public Integer getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(Integer loan_id) {
        this.loan_id = loan_id;
    }

    public BigDecimal getPrincipal_amount() {
        return principal_amount;
    }

    public void setPrincipal_amount(BigDecimal principal_amount) {
        this.principal_amount = principal_amount;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "loan_id=" + loan_id +
                ", principal_amount=" + principal_amount +
                ", totalamount=" + totalamount +
                ", balance=" + balance +
                '}';
    }
}
