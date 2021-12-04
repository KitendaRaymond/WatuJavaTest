package com.example.wetu;

import java.io.Serializable;
import java.math.BigDecimal;

public class Sales implements Serializable
{
    private String make_name;
    private BigDecimal sales;

    public String getMake_name() {
        return make_name;
    }

    public void setMake_name(String make_name) {
        this.make_name = make_name;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "make_name='" + make_name + '\'' +
                ", sales=" + sales +
                '}';
    }
}
