package ru.express.bank;

import ru.express.bank.annotaions.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Ioc {

    private Ioc() {
    }

    static Calculator createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new CalculatorImpl());
        return (Calculator) Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{Calculator.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final Calculator myClass;
        private final List<String> methodList;

        DemoInvocationHandler(Calculator myClass) {
            this.myClass = myClass;
            this.methodList = getMethods();
        }

        private List<String> getMethods() {
            Method[] mm = myClass.getClass().getDeclaredMethods();
            return Arrays.stream(mm)
                    .filter(m -> m.isAnnotationPresent(Log.class))
                    .map(Method::getName)
                    .collect(Collectors.toList());
        }

        private boolean isLog(Method method) {
            return methodList.contains(method.getName());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            if (isLog(method)) {
                System.out.printf("Executed method: %s; params: %s\n", method.getName(), Arrays.toString(args));
            }
            return method.invoke(myClass, args);
        }
    }
}
