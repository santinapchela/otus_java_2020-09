import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.express.bank.ATM;
import ru.express.bank.Banknote;
import ru.express.bank.CellOfBanknotes;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class ATMTests {
    ATM atm;

    @Before
    public void setup()
    {
        Map<Banknote, CellOfBanknotes> cells = new HashMap<>();
        cells.put(Banknote.NOTE_100, new CellOfBanknotes(1, Banknote.NOTE_100));
        cells.put(Banknote.NOTE_500, new CellOfBanknotes(1, Banknote.NOTE_500));
        cells.put(Banknote.NOTE_1000, new CellOfBanknotes(1, Banknote.NOTE_1000));
        cells.put(Banknote.NOTE_5000, new CellOfBanknotes(1, Banknote.NOTE_5000));

        atm = new ATM(cells);
    }


    @Test
    public void testPutCash()
    {
        Map<Banknote, Integer> cash = new HashMap();
        cash.put(Banknote.NOTE_100, 1);
        cash.put(Banknote.NOTE_500, 2);

        int sumCash = totalCash(cash);
        int beforeBalance = atm.getBalance();

        atm.putCash(cash);

        int afterBalance = atm.getBalance();

        assertEquals(beforeBalance + sumCash, afterBalance);
    }

    @Test
    public void testGetCashInvalidSum_1()
    {
        try {
            Map<Banknote, Integer> cash = atm.getCash(50000);
        }
        catch (Exception ex) {
            assertTrue(ex.getMessage().contains("Недостаточно денег в банкомате"));
        }
    }

    @Test
    public void testGetCashInvalidSum_2()
    {
        try {
            Map<Banknote, Integer> cash = atm.getCash(1400);
        }
        catch (Exception ex) {
            assertTrue(ex.getMessage().contains("Невозможно выдать запрашиваемую сумму"));
        }
    }

    @Test
    public void testGetCashInvalidSum_3()
    {
        try {
            Map<Banknote, Integer> cash = atm.getCash(100000);
        }
        catch (Exception ex) {
            assertTrue(ex.getMessage().contains("Недостаточно денег в банкомате"));
        }
    }

    @Test
    public void testGetCash()
    {
        try {
            int requiredSum = 1100;
            int beforeBalance = atm.getBalance();

            Map<Banknote, Integer> cash = atm.getCash(requiredSum);

            int afterBalance = atm.getBalance();

            assertEquals(beforeBalance - requiredSum, afterBalance);
        }
        catch (Exception ex) {
        }
    }

    private int totalCash(Map<Banknote, Integer> cash){
        int sum = 0;
        for (Map.Entry<Banknote, Integer> entry : cash.entrySet()) {
            sum += (entry.getKey().getValue() * entry.getValue());
        }

        return sum;
    }
}
