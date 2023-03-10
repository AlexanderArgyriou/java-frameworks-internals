package org.example.reflectionexample.reflection;

import org.example.reflectionexample.annotations.Dummy;

import java.util.Arrays;

public class Reflector {
    public static void inspectClass(Object o) {
        Class<?> c = o.getClass();
        if (Arrays.stream(c.getAnnotations()).anyMatch(a -> a instanceof Dummy)) {
            System.out.println("Do something, start a transaction for example");
        }
    }
}
