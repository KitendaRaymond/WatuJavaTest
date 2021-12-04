package com.example.wetu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.swing.text.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLWriter;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
public class WatuSampleController
{

    private static final Logger logger = LoggerFactory.getLogger(WatuSampleController.class);

    @Autowired
    DatabaseOperations databaseOperations;

    @RequestMapping("/bestSales")
    public List<Sales> bestSales()
    {
        return databaseOperations.getHighSales();
    }

    @RequestMapping("/allSales")
    public List<Sales> allSales()
    {
        return databaseOperations.getAllSales();
    }

    @RequestMapping("/getWeeklyPayments")
    public List<PaymentAmount> getWeeklyPayments()
    {
        return databaseOperations.getWeeklyPaymentAmount();
    }

    @RequestMapping("/getCurrentBalances")
    public List<Balance> getCurrentBalance()
    {
        return databaseOperations.getAllBalances();
    }

}
