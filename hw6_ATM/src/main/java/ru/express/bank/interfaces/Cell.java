package ru.express.bank.interfaces;

import ru.express.bank.exception.YouWantToManyMoneyException;

public interface Cell {

    public void addBanknotes(int quantity);

    public void getBanknotes(int quantity) throws YouWantToManyMoneyException;
}
