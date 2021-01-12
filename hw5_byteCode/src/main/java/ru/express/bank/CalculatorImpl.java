package ru.express.bank;

import ru.express.bank.annotaions.Log;

public class CalculatorImpl implements Calculator{

    @Log
    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Log
    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    @Log
    @Override
    public long multiply(long a, int b) {
        return a * b;
    }

    @Log
    @Override
    public double divide(double a, double c) {
        return a / c;
    }

    @Log
    @Override
    public String concat(String a, String b) {
        return a + b;
    }
}
