package ru.express.bank;

import ru.express.bank.test.TaxTest;

public class Main {

    public static void main(String[] args)
    {
        AnnotationAnalyzer analyzer = new AnnotationAnalyzer();
        analyzer.parse(TaxTest.class);
    }
}
