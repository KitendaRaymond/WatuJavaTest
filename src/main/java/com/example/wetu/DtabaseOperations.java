package com.example.wetu;

import java.util.List;

public interface DtabaseOperations
{
    public List<Sales> getHighSales();
    public List<Sales> getAllSales();
    List<PaymentAmount> getWeeklyPaymentAmount();
    List<Balance> getAllBalances();
}
