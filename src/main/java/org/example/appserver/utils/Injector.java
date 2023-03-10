package org.example.appserver.utils;

import org.example.appserver.annots.MyInjection;
import org.example.appserver.annots.MyTransaction;
import org.example.appserver.ctx.Context;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Injector {
    public static void performDependencyInjection(Context ctx) {
        ctx.getBeans().forEach(
                b -> {
                    Field[] fields = b.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        Annotation[] annotations = field.getAnnotations();
                        for (Annotation annotation : annotations) {
                            if (annotation instanceof MyInjection) {
                                try {
                                    Object impl = findImpl(ctx, field);
                                    field.setAccessible(true);
                                    field.set(b, getProxyClass(field.getType(), impl));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
        );
    }

    public static Object getProxyClass(Class<?> clazz, Object impl) {
        return Proxy.newProxyInstance( clazz.getClassLoader(), new Class[]{ clazz }, (proxy, method, args) -> {
            for ( Method m : impl.getClass().getMethods() ) {
                if ( m.getName().equals( method.getName() ) && m.getParameterCount() == method.getParameterCount() ) {
                    try {
                        for ( Annotation annotation : m.getAnnotations() ) {
                            if ( annotation instanceof MyTransaction) {
                                System.out.println( "Starting a transaction" );
                            }
                        }
                        System.out.println( "invoked from proxy" );
                        return m.invoke( impl, args );
                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        } );
    }

    private static Object findImpl(Context ctx, Field field) {
        for (Object bean : ctx.getBeans()) {
            Class<?>[] interfaces = bean.getClass().getInterfaces();
            if (Arrays.stream(interfaces).collect(Collectors.toSet()).contains(field.getType())) {
                return bean;
            }
        }
        return null;
    }
}
