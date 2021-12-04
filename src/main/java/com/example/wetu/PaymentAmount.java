package com.example.wetu;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaymentAmount implements Serializable
{
    private BigDecimal payment;
    private Integer loan_id;

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(Integer loan_id) {
        this.loan_id = loan_id;
    }

    @Override
    public String toString() {
        return "PaymentAmount{" +
                "payment=" + payment +
                ", loan_id=" + loan_id +
                '}';
    }
}
