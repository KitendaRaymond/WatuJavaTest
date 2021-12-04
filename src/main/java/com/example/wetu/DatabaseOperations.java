package com.example.wetu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

@Service
public class DatabaseOperations implements DtabaseOperations
{
    public DatabaseOperations() { super(); }

    private static final Logger logger = LoggerFactory.getLogger(DatabaseOperations.class);

    @Autowired
    private JdbcTemplate jdbctemplate;

    @Override
    public List<Sales> getHighSales()
    {

        try
        {
            String Query = "select count(a.m_loan_id) as sales,v.name as make_name from asset a inner join vehicle_model m on a.model_id = m.id" +
                    " inner join vehicle_make v on v.id = m.vehicle_make_id" +
                    " group by v.name" +
                    " order by sales desc";
            List<Sales> salesList = jdbctemplate.query
                    (Query, new BeanPropertyRowMapper<>(Sales.class));

            return salesList;
        }
        catch (Exception e)
        {
            logger.error(e.toString());
            return null;
        }

    }

    @Override
    public List<Sales> getAllSales()
    {

        try
        {
            String Query = "select count(a.m_loan_id) as sales,v.name as make_name from asset a right outer join vehicle_model m on a.model_id = m.id" +
                    " right outer join vehicle_make v on v.id = m.vehicle_make_id" +
                    " group by v.name" +
                    " order by sales desc";
            List<Sales> salesList = jdbctemplate.query
                    (Query, new BeanPropertyRowMapper<>(Sales.class));

            return salesList;
        }
        catch (Exception e)
        {
            logger.error(e.toString());
            return null;
        }

    }

    @Override
    public List<PaymentAmount> getWeeklyPaymentAmount()
    {

        try
        {
            String Query = "select `loan-schema`.m_loan_repayment_schedule.principal_amount+`loan-schema`.m_loan_repayment_schedule.interest_amount as payment," +
                    " `loan-schema`.m_loan_repayment_schedule.loan_id" +
                    " from `loan-schema`.m_loan_repayment_schedule" +
                    " where `loan-schema`.m_loan_repayment_schedule.completed_derived = 0" +
                    " group by`loan-schema`.m_loan_repayment_schedule.loan_id,payment";
            List<PaymentAmount> paymentsList = jdbctemplate.query
                    (Query, new BeanPropertyRowMapper<>(PaymentAmount.class));

            return paymentsList;
        }
        catch (Exception e)
        {
            logger.error(e.toString());
            return null;
        }

    }

    @Override
    public List<Balance> getAllBalances()
    {

        try
        {
            String Query = "select `loan-schema`.m_loan_repayment_schedule.loan_id, `loan-schema`.m_loan_repayment_schedule.principal_amount," +
                    " IFNULL(sum(`loan-schema`.m_loan_transaction.amount),0) as totalamount," +
                    " (`loan-schema`.m_loan_repayment_schedule.principal_amount - IFNULL(sum(`loan-schema`.m_loan_transaction.amount),0)) as balance" +
                    " from `loan-schema`.m_loan_transaction" +
                    " right outer join `loan-schema`.m_loan_repayment_schedule on `loan-schema`.m_loan_repayment_schedule.loan_id  = `loan-schema`.m_loan_transaction.loan_id" +
                    " group by `loan-schema`.m_loan_repayment_schedule.loan_id,`loan-schema`.m_loan_repayment_schedule.principal_amount" +
                    " order by `loan-schema`.m_loan_repayment_schedule.loan_id asc";

            List<Balance> balancesList = jdbctemplate.query
                    (Query, new BeanPropertyRowMapper<>(Balance.class));

            return balancesList;
        }
        catch (Exception e)
        {
            logger.error(e.toString());
            return null;
        }

    }

}
