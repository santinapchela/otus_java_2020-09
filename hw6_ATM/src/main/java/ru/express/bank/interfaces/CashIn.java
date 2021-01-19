package ru.express.bank.interfaces;

import ru.express.bank.Banknote;

import java.util.Map;

public interface CashIn {

    public void putCash(Map<Banknote, Integer> cash);
}
