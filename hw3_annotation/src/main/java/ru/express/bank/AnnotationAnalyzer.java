package ru.express.bank;

import ru.express.bank.annotations.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnnotationAnalyzer {

    public void parse(Class<?> clazz)
    {
        ArrayList<Method> beforList = new ArrayList<Method>();
        ArrayList<Method> testList = new ArrayList<Method>();
        ArrayList<Method> afterList = new ArrayList<Method>();
        int pass = 0;
        int fail = 0;
        HashMap<String, String> failInfo = new HashMap<String, String>();

        try {
            Method[] methods = clazz.getMethods();

            for (Method method : methods)
            {
                if (method.isAnnotationPresent(Befor.class)) {
                    beforList.add(method);
                } else if (method.isAnnotationPresent(Test.class)) {
                    testList.add(method);
                } else if (method.isAnnotationPresent(After.class)) {
                    afterList.add(method);
                }
            }

            for (Method testMethod : testList)
            {
                var obj = clazz.getDeclaredConstructor().newInstance();

                boolean isError = false;

                for (Method method : beforList) {
                    try {
                        method.invoke(obj);
                    } catch (InvocationTargetException ex) {
                        isError = true;
                        fail++;
                        failInfo.put(method.getName(), ex.getTargetException().getMessage());
                    }
                }

                if (!isError) {
                    try {
                        testMethod.invoke(obj);
                        pass++;
                    } catch (InvocationTargetException ex) {
                        fail++;
                        failInfo.put(testMethod.getName(), ex.getTargetException().getMessage());
                    }
                }

                for (Method method : afterList) {
                    try {
                    method.invoke(obj);
                    } catch (InvocationTargetException ex) { }
                }
            }
        }
        catch (Exception ex)
        {
            fail++;
        }

        int total = pass + fail;
        System.out.println("All test: " + total);
        System.out.println("Test passed: " + pass);
        System.out.println("Test failed: " + fail);

        for (Map.Entry<String, String> entry : failInfo.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
