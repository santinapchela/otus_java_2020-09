package ru.express.bank;

import ru.express.bank.interfaces.Cell;

public class CellOfBanknotes implements Cell {

    // Кол-во банкнот в ячейке
    private int quantity;
    // Номинал банкнот в ячейке
    private Banknote banknote;

    public CellOfBanknotes(int quantity, Banknote banknote)
    {
        this.quantity = quantity;
        this.banknote = banknote;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getBalanceByCell()
    {
        return quantity * banknote.getValue();
    }

    @Override
    public void addBanknotes(int quantity)
    {
        if (quantity < 0) {
            throw new IllegalArgumentException();
        }
        this.quantity += quantity;
    }

    @Override
    public void getBanknotes(int quantity)
    {
        if (quantity < 0 || this.quantity < quantity) {
            throw new IllegalArgumentException();
        }
        else {
            this.quantity -= quantity;
        }
    }
}
