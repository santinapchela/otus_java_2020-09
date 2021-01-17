package ru.express.bank;

import ru.express.bank.interfaces.CashIn;
import ru.express.bank.interfaces.CashOut;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ATM implements CashIn, CashOut {

    private Map<Banknote, CellOfBanknotes> cells;

    public int getBalance()
    {
        return cells.values().stream().map(item -> item.getBalanceByCell()).reduce((s1, s2) -> s1 + s2).orElse(0);
    }

    public ATM(Map<Banknote, CellOfBanknotes> cellsIn)
    {
        cells = new TreeMap<Banknote, CellOfBanknotes>(Collections.reverseOrder());
        cells.putAll(cellsIn);
    }

    @Override
    public Map<Banknote, Integer> getCash(int requiredSum) throws Exception {
        if (requiredSum > getBalance()) {
            throw new Exception("Недостаточно денег в банкомате");
        }

        Map<Banknote, Integer> needBanknote = new HashMap<Banknote, Integer>();
        for (Map.Entry<Banknote, CellOfBanknotes> entry : cells.entrySet()) {
            Banknote denomination = entry.getKey();
            int countBanknote = requiredSum / denomination.getValue();

            if (countBanknote <= 0) {
                continue;
            }

            int countInCell = cells.get(denomination).getQuantity();
            if (countInCell >= countBanknote) {
                needBanknote.put(denomination, countBanknote);
                cells.get(denomination).getBanknotes(countBanknote);
                requiredSum -= denomination.getValue() * countBanknote;
            }
        }

        if (requiredSum > 0) {
            throw new Exception("Невозможно выдать запрашиваемую сумму");
        }

        for (Map.Entry<Banknote, Integer> entry : needBanknote.entrySet()) {
            cells.get(entry.getKey()).getBanknotes(entry.getValue());
        }

        return needBanknote;
    }

    @Override
    public void putCash(Map<Banknote, Integer> cash) {
        for (Map.Entry<Banknote, Integer> entry : cash.entrySet()) {
            Banknote b = entry.getKey();
            int c = entry.getValue();

            cells.get(b).addBanknotes(c);
        }
    }

}
