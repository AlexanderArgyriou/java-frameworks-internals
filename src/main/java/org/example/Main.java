package org.example;

import org.example.appserver.annots.MyBean;
import org.example.appserver.beans.Bean1;
import org.example.appserver.ctx.Context;
import org.example.appserver.utils.ClassLoader;
import org.example.appserver.utils.Injector;
import org.example.proxyexample.impl.HeavyObjectImpl;
import org.example.proxyexample.intrf.HeavyObject;
import org.example.proxyexample.proxy.HeavyObjectProxy;
import org.example.reflectionexample.classes.RandomObject;
import org.example.reflectionexample.reflection.Reflector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HeavyObject heavyObject1 = new HeavyObjectImpl();
        HeavyObject heavyObject2 = new HeavyObjectProxy(heavyObject1);
        String wokChunk = "x";

        heavyObject1.operation(wokChunk);
        heavyObject2.operation(wokChunk);

        Reflector.inspectClass(new RandomObject());

        new ClassLoader().loadAllClassesInClassPath("./target/classes/org/example/appserver/beans")
                .forEach(c -> {
                    if (Arrays.stream(c.getAnnotations()).anyMatch(annot -> annot instanceof MyBean)) {
                        try {
                            Context.getCtx().addToContext(c.getDeclaredConstructor().newInstance());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
        System.out.println(Context.getCtx());

        Injector.performDependencyInjection(Context.getCtx());
        ((Bean1) Context.getCtx().getFromContext(Bean1.class)).getBean2().doSomething();

    }
}