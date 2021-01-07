package ru.express.bank;

public class ProxyDemo {
    public static void main(String[] args) {
        Calculator myCalc = Ioc.createMyClass();
        System.out.println( myCalc.sum(20, 30) );
        System.out.println( myCalc.divide(30, 2) );
        System.out.println( myCalc.multiply(20, 2) );
        System.out.println( myCalc.subtract(100, 34) );
        System.out.println( myCalc.concat("Каждый охотник ", "желает знать") );
    }
}



