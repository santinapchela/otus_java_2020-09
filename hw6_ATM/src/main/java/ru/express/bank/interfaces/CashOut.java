package ru.express.bank.interfaces;

import ru.express.bank.Banknote;

import java.util.Map;

public interface CashOut {

    public Map<Banknote, Integer> getCash(int value) throws Exception ;
}
