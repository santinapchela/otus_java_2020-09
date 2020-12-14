package ru.express.bank.bank;

import java.util.Date;

public class Deposit extends Account{
    private Double percentRate;
    private Date dateEnd;

    public Deposit(String name, String currancy, Double amount, Date dateBegin, Date dateEnd, Double percentRate) {
        super(name, currancy, amount, dateBegin);
        this.percentRate = percentRate;
        this.dateEnd = dateEnd;
    }

    public void setPercentRate(Double percentRate)
    {
        this.percentRate = percentRate;
    }

    public Double getPercentRate()
    {
        return this.percentRate;
    }

    public void setdDateEnd(Date dateEnd)
    {
        this.dateEnd = dateEnd;
    }

    public Date getDateEnd()
    {
        return this.dateEnd;
    }
}
