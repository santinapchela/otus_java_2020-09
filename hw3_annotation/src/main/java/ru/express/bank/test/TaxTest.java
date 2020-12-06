package ru.express.bank.test;


import ru.express.bank.annotations.*;
import ru.express.bank.bank.Deposit;
import ru.express.bank.bank.Tax;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaxTest {

    private Tax tax;
    private List<Deposit>  depositList_HighBase;
    private List<Deposit>  depositList_LowBase;

    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

    @Befor
    public void befor()
    {
        try
        {
            tax = new Tax();

            Deposit deposit1 = new Deposit("Вклад весенний", "RUR", 900000.0, ft.parse("2020-03-01"), ft.parse("2021-03-01"), 7.25);
            Deposit deposit2 = new Deposit("Вклад летний", "RUR", 200000.0, ft.parse("2020-07-01"), ft.parse("2021-07-01"), 5.0);
            Deposit deposit3 = new Deposit("Вклад осенний", "RUR", 300000.0, ft.parse("2020-10-01"), ft.parse("2021-10-01"), 4.25);

            depositList_HighBase = new ArrayList<>();
            depositList_HighBase.add(deposit1);
            depositList_HighBase.add(deposit2);

            depositList_LowBase = new ArrayList<>();
            depositList_LowBase.add(deposit2);
            depositList_LowBase.add(deposit3);
        }
        catch (Exception ex)
        {

        }
    }

    @Test
    public void higthBaseTest()
    {
        Double taxAmount = tax.calcTaxAmount(depositList_HighBase);
        if (taxAmount <= 0.0)
        {
            throw new ArithmeticException("Ошибка при расчете налога с процентов по вкладам суммой больше 1 000 000");
        }
    }

    @Test
    public void lowBaseTest()
    {
        Double taxAmount = tax.calcTaxAmount(depositList_LowBase);
        if (!taxAmount.equals(0.0))
        {
            throw new ArithmeticException("Ошибка при расчете налога с процентов по вкладам суммой меньше 1 000 000");
        }
    }


    @After
    public void after()
    {
        tax = null;
        depositList_LowBase = null;
        depositList_HighBase = null;
    }
}

