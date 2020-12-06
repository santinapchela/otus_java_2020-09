package ru.express.bank.bank;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

// Класс для расчета налога с процентов по вкладам физлиц, если сумма вкладов превышает 1 млн рублей
public class Tax {

    // Сумма вкладов необлагаемых налогом
    private Double NON_TAX_BASE_AMOUNT = 1000000.0;
    // Ключевая ставка ЦБ
    private Double KEY_RATE = 4.25;
    // Ставка налогооблажения
    private Double TAX = 13.0;

    public Double calcTaxAmount(List<Deposit> depositList)
    {
        Double incomeAmount = 0.0; // Сумма дохода со всех вкладов
        Double nonTaxAmount = NON_TAX_BASE_AMOUNT * KEY_RATE / 100;

        for (Deposit deposit : depositList)
        {
            // Срок вклада в днях
            Long days = getDifferenceDays(deposit.getDateBegin(), deposit.getDateEnd());
            // Расчет доходности вклада за весь период
            incomeAmount += (deposit.getAmount() * days * deposit.getPercentRate() / (100 * 365));
        }

        Double taxAmount = (incomeAmount - nonTaxAmount) * TAX / 100;
        if (taxAmount <= 0.0) {
            return 0.0;
        }

        return taxAmount;
    }

    public static long getDifferenceDays(Date firstDate, Date secondDate) {
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
